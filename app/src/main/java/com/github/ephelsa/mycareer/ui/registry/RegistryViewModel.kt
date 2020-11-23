package com.github.ephelsa.mycareer.ui.registry

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import kotlinx.coroutines.CoroutineDispatcher

class RegistryViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    private val registerAUserUseCase: RegisterAUserUseCase,
    countriesUseCase: CountriesUseCase,
    private val departmentsByCountryUseCase: DepartmentsByCountryUseCase,
    private val municipalitiesByCountryAndDepartmentUseCase: MunicipalitiesByCountryAndDepartmentUseCase
) : ScopedViewModel(uiDispatcher) {

    val countries: LiveData<ResourceRemote<List<CountryRemote>>> = countriesUseCase().asLiveData()

    fun departments(countryCode: String) = departmentsByCountryUseCase(countryCode).asLiveData()

    fun municipalities(countryCode: String, departmentCode: String) =
        municipalitiesByCountryAndDepartmentUseCase(countryCode, departmentCode).asLiveData()

    fun sendRegistry(registry: RegistryRemote) = registerAUserUseCase(registry).asLiveData()
}
