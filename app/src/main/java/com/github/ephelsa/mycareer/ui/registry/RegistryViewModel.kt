package com.github.ephelsa.mycareer.ui.registry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import com.github.ephelsa.mycareer.usecase.documenttype.DocumentTypesUseCase
import com.github.ephelsa.mycareer.usecase.institutiontype.InstitutionTypesUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import com.github.ephelsa.mycareer.usecase.studylevel.StudyLevelsUseCase
import kotlinx.coroutines.CoroutineDispatcher

class RegistryViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    countriesUseCase: CountriesUseCase,
    documentTypesUseCase: DocumentTypesUseCase,
    institutionTypesUseCase: InstitutionTypesUseCase,
    studyLevelsUseCase: StudyLevelsUseCase,
    private val registerAUserUseCase: RegisterAUserUseCase,
    private val departmentsByCountryUseCase: DepartmentsByCountryUseCase,
    private val municipalitiesByCountryAndDepartmentUseCase: MunicipalitiesByCountryAndDepartmentUseCase,
) : ScopedViewModel(uiDispatcher) {

    private val _downloadDepartments = MutableLiveData<CountryRemote>()
    val downloadDepartments: LiveData<CountryRemote> = _downloadDepartments

    private val _downloadMunicipalities = MutableLiveData<DepartmentRemote>()
    val downloadMunicipalities: LiveData<DepartmentRemote> = _downloadMunicipalities

    private val _ui = MutableLiveData<UI>()
    val ui: LiveData<UI> get() = _ui

    val countries = countriesUseCase().asLiveData()

    val documentTypes = documentTypesUseCase().asLiveData()

    val institutionTypes = institutionTypesUseCase().asLiveData()

    val studyLevels = studyLevelsUseCase().asLiveData()

    fun departments(countryCode: String) = departmentsByCountryUseCase(countryCode).asLiveData()

    fun municipalities(countryCode: String, departmentCode: String) =
        municipalitiesByCountryAndDepartmentUseCase(countryCode, departmentCode).asLiveData()

    fun sendRegistry(registry: RegistryRemote) = registerAUserUseCase(registry).asLiveData()

    fun validateCountry(countryRemote: CountryRemote?) {
        countryRemote?.let { _downloadDepartments.value = it }
        _ui.value = UI.InvalidCountry(countryRemote == null)
    }

    fun validateDepartment(departmentRemote: DepartmentRemote?) {
        departmentRemote?.let { _downloadMunicipalities.value = it }
        _ui.value = UI.InvalidDepartment(departmentRemote == null)
    }

    fun validateMunicipality(municipalityRemote: MunicipalityRemote?) {
        _ui.value = UI.InvalidMunicipality(municipalityRemote == null)
    }

    fun validateDocumentType(documentTypeRemote: DocumentTypeRemote?) {
        _ui.value = UI.InvalidDocumentType(documentTypeRemote == null)
    }

    fun validateStudyLevel(studyLevelRemote: StudyLevelRemote?) {
        _ui.value = UI.InvalidStudyLevel(studyLevelRemote == null)
    }

    fun validateInstitutionType(institutionTypeRemote: InstitutionTypeRemote?) {
        _ui.value = UI.InstitutionType(institutionTypeRemote == null)
    }

    sealed class UI {
        data class InvalidCountry(val isInvalid: Boolean) : UI()
        data class InvalidDepartment(val isInvalid: Boolean) : UI()
        data class InvalidMunicipality(val isInvalid: Boolean) : UI()
        data class InvalidDocumentType(val isInvalid: Boolean) : UI()
        data class InvalidStudyLevel(val isInvalid: Boolean) : UI()
        data class InstitutionType(val isInvalid: Boolean) : UI()
    }
}
