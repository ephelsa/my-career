package com.github.ephelsa.mycareer.ui.registry

import android.util.Patterns
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
import com.github.ephelsa.mycareer.ui.utils.isNumberValid
import com.github.ephelsa.mycareer.ui.utils.isTextValid
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import com.github.ephelsa.mycareer.usecase.documenttype.DocumentTypesUseCase
import com.github.ephelsa.mycareer.usecase.institutiontype.InstitutionTypesUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import com.github.ephelsa.mycareer.usecase.studylevel.StudyLevelsUseCase
import kotlinx.coroutines.CoroutineDispatcher

private data class SendRegistryRequirements(
    var firstName: Boolean = false,
    var secondName: Boolean = true,
    var firstSurname: Boolean = false,
    var secondSurname: Boolean = false,
    var documentType: Boolean = false,
    var documentId: Boolean = false,
    var country: Boolean = false,
    var department: Boolean = false,
    var municipality: Boolean = false,
    var studyLevel: Boolean = false,
    var institutionType: Boolean = false,
    var institutionName: Boolean = false,
    var email: Boolean = false,
    var password: Boolean = false,
)

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

    private val registryRequirements = SendRegistryRequirements()

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
        val isInvalid = countryRemote == null
        countryRemote?.let { _downloadDepartments.value = it }
        handleRegistryRequirements { country = !isInvalid }
        _ui.value = UI.InvalidCountry(isInvalid)
    }

    fun validateDepartment(departmentRemote: DepartmentRemote?) {
        val isInvalid = departmentRemote == null
        departmentRemote?.let { _downloadMunicipalities.value = it }
        handleRegistryRequirements { department = !isInvalid }
        _ui.value = UI.InvalidDepartment(isInvalid)
    }

    fun validateMunicipality(municipalityRemote: MunicipalityRemote?) {
        val isInvalid = municipalityRemote == null
        handleRegistryRequirements { municipality = !isInvalid }
        _ui.value = UI.InvalidMunicipality(isInvalid)
    }

    fun validateDocumentType(documentTypeRemote: DocumentTypeRemote?) {
        val isInvalid = documentTypeRemote == null
        handleRegistryRequirements { documentType = !isInvalid }
        _ui.value = UI.InvalidDocumentType(isInvalid)
    }

    fun validateStudyLevel(studyLevelRemote: StudyLevelRemote?) {
        val isInvalid = studyLevelRemote == null
        handleRegistryRequirements { studyLevel = !isInvalid }
        _ui.value = UI.InvalidStudyLevel(isInvalid)
    }

    fun validateInstitutionType(institutionTypeRemote: InstitutionTypeRemote?) {
        val isInvalid = institutionTypeRemote == null
        handleRegistryRequirements { institutionType = !isInvalid }
        _ui.value = UI.InstitutionType(isInvalid)
    }

    fun validateEmail(email: String) {
        val isInvalid = !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        handleRegistryRequirements { this.email = !isInvalid }
        _ui.value = UI.InvalidEmail(isInvalid)
    }

    fun validateFirstName(firstName: String) {
        val isInvalid = firstName.isTextInvalid()
        handleRegistryRequirements { this.firstName = !isInvalid }
        _ui.value = UI.InvalidFirstName(isInvalid)
    }

    fun validateSecondName(secondName: String) {
        val isInvalid = secondName.isTextInvalid() && secondName.isNotEmpty()
        handleRegistryRequirements { this.secondName = !isInvalid }
        _ui.value = UI.InvalidSecondName(isInvalid)
    }

    fun validateFirstSurname(firstSurname: String) {
        val isInvalid = firstSurname.isTextInvalid()
        handleRegistryRequirements { this.firstSurname = !isInvalid }
        _ui.value = UI.InvalidFirstSurname(isInvalid)
    }

    fun validateSecondSurname(secondSurname: String) {
        val isInvalid = secondSurname.isTextInvalid()
        handleRegistryRequirements { this.secondSurname = !isInvalid }
        _ui.value = UI.InvalidSecondSurname(isInvalid)
    }

    fun validateDocumentId(document: String) {
        val isInvalid = document.isNumberInvalid()
        handleRegistryRequirements { documentId = !isInvalid }
        _ui.value = UI.InvalidDocumentId(isInvalid)
    }

    fun validateInstitutionName(name: String) {
        val isInvalid = name.isTextInvalid()
        handleRegistryRequirements { institutionName = !isInvalid }
        _ui.value = UI.InvalidInstitutionName(isInvalid)
    }

    fun validatePassword(pass: String, confirm: String, minPassLen: Int) {
        val isInvalid = pass != confirm || pass.length < minPassLen
        handleRegistryRequirements { password = !isInvalid }
        _ui.value = UI.InvalidPassword(isInvalid)
    }

    private fun handleRegistryRequirements(r: SendRegistryRequirements.() -> Unit) {
        registryRequirements.apply(r)
        with(registryRequirements) {
            val enable = firstName && secondName && firstSurname && secondSurname && documentType &&
                documentId && country && department && municipality && studyLevel &&
                institutionType && institutionName && email && password
            _ui.value = UI.EnableRegistryButton(enable)
        }
    }

    private fun String.isTextInvalid(): Boolean {
        val parsed = this.trim()
        return parsed.isEmpty() || !parsed.isTextValid()
    }

    private fun String.isNumberInvalid() = !isNumberValid()

    sealed class UI {
        data class InvalidCountry(val isInvalid: Boolean) : UI()
        data class InvalidDepartment(val isInvalid: Boolean) : UI()
        data class InvalidMunicipality(val isInvalid: Boolean) : UI()
        data class InvalidDocumentType(val isInvalid: Boolean) : UI()
        data class InvalidStudyLevel(val isInvalid: Boolean) : UI()
        data class InstitutionType(val isInvalid: Boolean) : UI()
        data class InvalidEmail(val isInvalid: Boolean) : UI()
        data class InvalidFirstName(val isInvalid: Boolean) : UI()
        data class InvalidSecondName(val isInvalid: Boolean) : UI()
        data class InvalidFirstSurname(val isInvalid: Boolean) : UI()
        data class InvalidSecondSurname(val isInvalid: Boolean) : UI()
        data class InvalidDocumentId(val isInvalid: Boolean) : UI()
        data class InvalidInstitutionName(val isInvalid: Boolean) : UI()
        data class InvalidPassword(val isInvalid: Boolean) : UI()
        data class EnableRegistryButton(val isEnabled: Boolean) : UI()
    }
}
