package com.github.ephelsa.mycareer.ui.registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentRegistryBinding
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import com.github.ephelsa.mycareer.ui.registry.RegistryViewModel.UI
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.isErrorEnabled
import com.github.ephelsa.mycareer.ui.utils.isLoading
import com.github.ephelsa.mycareer.ui.utils.match
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryFragment : BaseFragment<FragmentRegistryBinding>(), View.OnClickListener {

    private val viewModel: RegistryViewModel by viewModels()

    private val autoListStyle = android.R.layout.simple_dropdown_item_1line

    private lateinit var institutionTypes: List<InstitutionTypeRemote>
    private lateinit var studyLevels: List<StudyLevelRemote>
    private lateinit var documentTypes: List<DocumentTypeRemote>
    private lateinit var countries: List<CountryRemote>
    private lateinit var departments: List<DepartmentRemote>
    private lateinit var municipalities: List<MunicipalityRemote>

    companion object {
        private val TAG = RegistryFragment::class.java.simpleName
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistryBinding {
        return FragmentRegistryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListener()

        loadCountries()
        loadDocumentTypes()
        loadStudyLevels()
        loadInstitutionTypes()
        viewModel.ui.observe(viewLifecycleOwner, Observer(::uiObserver))
    }

    private fun bindClickListener() {
        with(binding) {
            loginButton.setOnClickListener(this@RegistryFragment)
            backButton.setOnClickListener(this@RegistryFragment)
            completeRegistryButton.setOnClickListener(this@RegistryFragment)
        }
    }

    private fun loadInstitutionTypes() {
        viewModel.institutionTypes.handleObservable(
            enableDefaultLoader = false,
            onLoading = {
                binding.institutionTypeContainer
                    .isLoading(true, getString(R.string.loading_institution_types))
            },
            onSuccess = {
                institutionTypes = it.data
                val adapter = ArrayAdapter(requireContext(), autoListStyle, institutionTypes)
                binding.institutionTypeAuto.setAdapter(adapter)
                validateInstitutionType()
            },
            onComplete = {
                binding.institutionTypeContainer.isLoading(false)
            }
        )
    }

    private fun validateInstitutionType() {
        val auto = binding.institutionTypeAuto
        auto.addTextChangedListener { e ->
            viewModel.validateInstitutionType(institutionTypes.match(e.toString()) { it.name })
        }
    }

    private fun loadStudyLevels() {
        viewModel.studyLevels.handleObservable(
            enableDefaultLoader = false,
            onLoading = {
                binding.studyLevelContainer
                    .isLoading(true, getString(R.string.loading_study_levels))
            },
            onSuccess = {
                studyLevels = it.data
                val adapter = ArrayAdapter(requireContext(), autoListStyle, studyLevels)
                binding.studyLevelAuto.setAdapter(adapter)
                validateStudyLevel()
            },
            onComplete = {
                binding.studyLevelContainer.isLoading(false)
            }
        )
    }

    private fun validateStudyLevel() {
        val auto = binding.studyLevelAuto
        auto.addTextChangedListener { e ->
            viewModel.validateStudyLevel(studyLevels.match(e.toString()) { it.name })
        }
    }

    private fun loadDocumentTypes() {
        viewModel.documentTypes.handleObservable(
            enableDefaultLoader = false,
            onLoading = {
                binding.documentTypeContainer
                    .isLoading(true, getString(R.string.loading_document_types))
            },
            onSuccess = {
                documentTypes = it.data
                val adapter = ArrayAdapter(requireContext(), autoListStyle, documentTypes)
                binding.documentTypeAuto.setAdapter(adapter)
                validateDocumentType()
            },
            onComplete = {
                binding.documentTypeContainer.isLoading(false)
            }
        )
    }

    private fun validateDocumentType() {
        val auto = binding.documentTypeAuto
        auto.addTextChangedListener { e ->
            viewModel.validateDocumentType(documentTypes.match(e.toString()) { it.name })
        }
    }

    private fun loadCountries() {
        viewModel.countries.handleObservable(
            enableDefaultLoader = false,
            onLoading = {
                binding.countryContainer.isLoading(true, getString(R.string.loading_countries))
            },
            onSuccess = {
                countries = it.data
                val adapter = ArrayAdapter(requireContext(), autoListStyle, countries)
                binding.countryAuto.setAdapter(adapter)
                validateCountry()
                loadDepartmentsByCountry()
            },
            onComplete = {
                binding.countryContainer.isLoading(false)
            }
        )
    }

    private fun validateCountry() {
        val auto = binding.countryAuto
        auto.addTextChangedListener { e ->
            viewModel.validateCountry(countries.match(e.toString()) { it.name })
        }
    }

    private fun loadDepartmentsByCountry() {
        viewModel.downloadDepartments.observe(viewLifecycleOwner) { country ->
            viewModel.departments(country.code).handleObservable(
                onSuccess = {
                    departments = it.data
                    val adapter = ArrayAdapter(requireContext(), autoListStyle, departments)
                    binding.departmentAuto.setAdapter(adapter)
                    validateDepartment()
                    loadMunicipalitiesByCountryAndDepartment()
                }
            )
        }
    }

    private fun validateDepartment() {
        val auto = binding.departmentAuto
        auto.addTextChangedListener { e ->
            viewModel.validateDepartment(departments.match(e.toString()) { it.name })
        }
    }

    private fun loadMunicipalitiesByCountryAndDepartment() {
        viewModel.downloadMunicipalities.observe(viewLifecycleOwner) { d ->
            viewModel.municipalities(d.countryCode, d.departmentCode).handleObservable(
                onSuccess = {
                    municipalities = it.data
                    val adapter = ArrayAdapter(requireContext(), autoListStyle, municipalities)
                    binding.municipalityAuto.setAdapter(adapter)
                    validateMunicipality()
                }
            )
        }
    }

    private fun validateMunicipality() {
        val auto = binding.municipalityAuto
        auto.addTextChangedListener { e ->
            viewModel.validateMunicipality(municipalities.match(e.toString()) { it.name })
        }
    }

    private fun performRegistry() {
        Toast.makeText(requireContext(), getString(R.string.app_name), Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginButton -> findNavController().popBackStack()
            binding.backButton -> findNavController().popBackStack()
            binding.completeRegistryButton -> performRegistry()
        }
    }

    private fun uiObserver(ui: UI) {
        when (ui) {
            is UI.InvalidCountry -> handleInvalidCountry(ui.isInvalid)
            is UI.InvalidDepartment -> handleInvalidDepartment(ui.isInvalid)
            is UI.InvalidMunicipality -> handleInvalidMunicipality(ui.isInvalid)
            is UI.InvalidDocumentType -> {
                binding.documentTypeContainer
                    .isErrorEnabled(ui.isInvalid, getString(R.string.error_document_type))
            }
            is UI.InvalidStudyLevel -> {
                binding.studyLevelContainer
                    .isErrorEnabled(ui.isInvalid, getString(R.string.error_study_level))
            }
            is UI.InstitutionType -> {
                binding.institutionTypeContainer
                    .isErrorEnabled(ui.isInvalid, getString(R.string.error_institution_type))
            }
        }
    }

    private fun handleInvalidCountry(isInvalid: Boolean) {
        with(binding) {
            countryContainer.isErrorEnabled(isInvalid, getString(R.string.error_country))
            departmentAuto.apply {
                text.clear()
                setAdapter(null)
            }
            municipalityAuto.apply {
                text.clear()
                setAdapter(null)
            }
        }
    }

    private fun handleInvalidDepartment(isInvalid: Boolean) {
        with(binding) {
            departmentContainer
                .isErrorEnabled(isInvalid, getString(R.string.error_department))
            municipalityAuto.apply {
                text.clear()
                setAdapter(null)
            }
        }
    }

    private fun handleInvalidMunicipality(isInvalid: Boolean) {
        binding.municipalityContainer
            .isErrorEnabled(isInvalid, getString(R.string.error_municipality))
    }
}
