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
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.ui.registry.RegistryViewModel.UI
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.isErrorEnabled
import com.github.ephelsa.mycareer.ui.utils.match
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryFragment : BaseFragment<FragmentRegistryBinding>(), View.OnClickListener {

    private val viewModel: RegistryViewModel by viewModels()

    private val autoListStyle = android.R.layout.simple_dropdown_item_1line
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
        viewModel.ui.observe(viewLifecycleOwner, Observer(::uiObserver))
    }

    private fun bindClickListener() {
        with(binding) {
            loginButton.setOnClickListener(this@RegistryFragment)
            backButton.setOnClickListener(this@RegistryFragment)
            completeRegistryButton.setOnClickListener(this@RegistryFragment)
        }
    }

    private fun loadCountries() {
        viewModel.countries.handleObservable(
            onSuccess = {
                countries = it.data
                val adapter = ArrayAdapter(requireContext(), autoListStyle, countries)
                binding.countryAuto.setAdapter(adapter)
                validateCountry()
                loadDepartmentsByCountry()
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
