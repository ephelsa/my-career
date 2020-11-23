package com.github.ephelsa.mycareer.ui.registry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.data.location.LocationRepository
import com.github.ephelsa.mycareer.infraestructure.auth.remote.AuthRemoteRepository
import com.github.ephelsa.mycareer.infraestructure.location.remote.LocationRemoteRepository
import com.github.ephelsa.mycareer.infraestructure.shared.remote.RetrofitBuild
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.create

class RegistryViewModelProvider : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val authRemoteRepository = AuthRemoteRepository(RetrofitBuild.retrofit.create())
        val authRepository = AuthRepository(authRemoteRepository)

        val locationRemoteRepository = LocationRemoteRepository(RetrofitBuild.retrofit.create())
        val locationRepository = LocationRepository(locationRemoteRepository)

        return modelClass.getConstructor(
            CoroutineDispatcher::class.java,
            RegisterAUserUseCase::class.java,
            CountriesUseCase::class.java,
            DepartmentsByCountryUseCase::class.java,
            MunicipalitiesByCountryAndDepartmentUseCase::class.java
        ).newInstance(
            Dispatchers.IO,
            RegisterAUserUseCase(authRepository),
            CountriesUseCase(locationRepository),
            DepartmentsByCountryUseCase(locationRepository),
            MunicipalitiesByCountryAndDepartmentUseCase(locationRepository)
        )
    }
}
