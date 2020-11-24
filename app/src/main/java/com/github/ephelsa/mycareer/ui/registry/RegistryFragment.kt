package com.github.ephelsa.mycareer.ui.registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentRegistryBinding
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import com.github.ephelsa.mycareer.ui.dialog.DialogListener
import com.github.ephelsa.mycareer.ui.dialog.SuccessDialog
import com.github.ephelsa.mycareer.ui.registry.RegistryViewModel.UI
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.hasError
import com.github.ephelsa.mycareer.ui.utils.isLoading
import com.github.ephelsa.mycareer.ui.utils.match
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryFragment :
    BaseFragment<FragmentRegistryBinding>(), View.OnClickListener, DialogListener {

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
        private const val MIN_PASSWORD_SIZE = 8
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
        validateNoAutoFields()
    }

    override fun onStart() {
        super.onStart()
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

    private fun validateNoAutoFields() {
        with(binding) {
            firstNameEdit.addTextChangedListener {
                viewModel.validateFirstName(it.toString())
            }

            secondNameEdit.addTextChangedListener {
                viewModel.validateSecondName(it.toString())
            }

            firstSurnameEdit.addTextChangedListener {
                viewModel.validateFirstSurname(it.toString())
            }

            secondSurnameEdit.addTextChangedListener {
                viewModel.validateSecondSurname(it.toString())
            }

            documentEdit.addTextChangedListener {
                viewModel.validateDocumentId(it.toString())
            }

            institutionNameEdit.addTextChangedListener {
                viewModel.validateInstitutionName(it.toString())
            }

            emailEdit.addTextChangedListener {
                viewModel.validateEmail(it.toString())
            }

            passwordEdit.addTextChangedListener {
                viewModel.validatePassword(
                    it.toString(),
                    passwordConfirmEdit.text.toString(),
                    MIN_PASSWORD_SIZE
                )
            }

            passwordConfirmEdit.addTextChangedListener {
                viewModel.validatePassword(
                    passwordEdit.text.toString(),
                    it.toString(),
                    MIN_PASSWORD_SIZE
                )
            }
        }
    }

    private fun loadInstitutionTypes() {
        viewModel.institutionTypes.handleObservable(
            enableDefaultLoader = false,
            enableDefaultError = false,
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
            onError = {
                binding.institutionTypeContainer.hasError(true, it.error.message)
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
            enableDefaultError = false,
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
            onError = {
                binding.studyLevelContainer.hasError(true, it.error.message)
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
            enableDefaultError = false,
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
            onError = {
                binding.documentTypeContainer.hasError(true, it.error.message)
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
            enableDefaultError = false,
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
            onError = {
                binding.countryContainer.hasError(true, it.error.message)
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
        val documentType = documentTypes.match(binding.documentTypeAuto.text.toString()) {
            it.name
        }?.id
        val countryCode = countries.match(binding.countryAuto.text.toString()) { it.name }?.code
        val departmentCode = departments.match(binding.departmentAuto.text.toString()) {
            it.name
        }?.departmentCode
        val municipalityCode = municipalities.match(binding.municipalityAuto.text.toString()) {
            it.name
        }?.municipalityCode
        val studyLevelCode = studyLevels.match(binding.studyLevelAuto.text.toString()) {
            it.name
        }?.id
        val instType = institutionTypes.match(binding.institutionTypeAuto.text.toString()) {
            it.name
        }?.id
        val reg = RegistryRemote(
            firstName = binding.firstNameEdit.text.toString(),
            secondName = binding.secondNameEdit.text.toString(),
            firstSurname = binding.firstSurnameEdit.text.toString(),
            secondSurname = binding.secondSurnameEdit.text.toString(),
            documentType = documentType ?: "",
            document = binding.documentEdit.text.toString(),
            countryCode = countryCode ?: "",
            departmentCode = departmentCode ?: "",
            municipalityCode = municipalityCode ?: "",
            studyLevelCode = studyLevelCode ?: -1,
            institutionType = instType ?: -1,
            institutionName = binding.institutionNameEdit.text.toString(),
            emailAddress = binding.emailEdit.text.toString(),
            password = binding.passwordEdit.text.toString()
        )
        viewModel.sendRegistry(reg).handleObservable(
            onSuccess = {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.success_registry, it.data.email),
                    Toast.LENGTH_LONG
                ).show()
                displaySuccess(this)
            }
        )
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
            is UI.InvalidDocumentType -> handleInvalidDocumentType(ui.isInvalid)
            is UI.InvalidStudyLevel -> handleInvalidStudyLevel(ui.isInvalid)
            is UI.InstitutionType -> handleInstitutionType(ui.isInvalid)
            is UI.InvalidEmail -> handleInvalidEmail(ui.isInvalid)
            is UI.InvalidFirstName -> handleInvalidFirstName(ui.isInvalid)
            is UI.InvalidSecondName -> handleInvalidSecondName(ui.isInvalid)
            is UI.InvalidFirstSurname -> handleInvalidFirstSurname(ui.isInvalid)
            is UI.InvalidSecondSurname -> handleInvalidSecondSurname(ui.isInvalid)
            is UI.InvalidDocumentId -> handleInvalidDocumentId(ui.isInvalid)
            is UI.InvalidInstitutionName -> handleInvalidInstitutionName(ui.isInvalid)
            is UI.InvalidPassword -> handleInvalidPassword(ui.isInvalid)
            is UI.EnableRegistryButton -> binding.completeRegistryButton.isEnabled = ui.isEnabled
        }
    }

    private fun handleInvalidDocumentType(isInvalid: Boolean) {
        binding.documentTypeContainer
            .hasError(isInvalid, getString(R.string.error_document_type))
    }

    private fun handleInvalidStudyLevel(isInvalid: Boolean) {
        binding.studyLevelContainer
            .hasError(isInvalid, getString(R.string.error_study_level))
    }

    private fun handleInstitutionType(isInvalid: Boolean) {
        binding.institutionTypeContainer
            .hasError(isInvalid, getString(R.string.error_institution_type))
    }

    private fun handleInvalidEmail(isInvalid: Boolean) {
        binding.emailContainer
            .hasError(isInvalid, getString(R.string.error_email))
    }

    private fun handleInvalidFirstName(isInvalid: Boolean) {
        binding.firstNameContainer
            .hasError(isInvalid, getString(R.string.error_first_name))
    }

    private fun handleInvalidSecondName(isInvalid: Boolean) {
        binding.secondNameContainer
            .hasError(isInvalid, getString(R.string.error_second_name))
    }

    private fun handleInvalidFirstSurname(isInvalid: Boolean) {
        binding.firstSurnameContainer
            .hasError(isInvalid, getString(R.string.error_first_surname))
    }

    private fun handleInvalidSecondSurname(isInvalid: Boolean) {
        binding.secondSurnameContainer
            .hasError(isInvalid, getString(R.string.error_second_surname))
    }

    private fun handleInvalidDocumentId(isInvalid: Boolean) {
        binding.documentContainer
            .hasError(isInvalid, getString(R.string.error_document))
    }

    private fun handleInvalidInstitutionName(isInvalid: Boolean) {
        binding.institutionNameContainer
            .hasError(isInvalid, getString(R.string.error_institution_name))
    }

    private fun handleInvalidPassword(isInvalid: Boolean) {
        binding.passwordContainer
            .hasError(
                isInvalid,
                getString(R.string.error_password_match_length, MIN_PASSWORD_SIZE)
            )

        binding.passwordConfirmContainer
            .hasError(
                isInvalid,
                getString(R.string.error_password_match_length, MIN_PASSWORD_SIZE)
            )
    }

    private fun handleInvalidCountry(isInvalid: Boolean) {
        with(binding) {
            countryContainer.hasError(isInvalid, getString(R.string.error_country))
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
                .hasError(isInvalid, getString(R.string.error_department))
            municipalityAuto.apply {
                text.clear()
                setAdapter(null)
            }
        }
    }

    private fun handleInvalidMunicipality(isInvalid: Boolean) {
        binding.municipalityContainer
            .hasError(isInvalid, getString(R.string.error_municipality))
    }

    override fun onClose(dialogFragment: DialogFragment) {
        when (dialogFragment) {
            is SuccessDialog -> findNavController().popBackStack()
        }
    }
}
