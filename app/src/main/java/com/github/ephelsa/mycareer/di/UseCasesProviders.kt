package com.github.ephelsa.mycareer.di

import com.github.ephelsa.mycareer.delivery.auth.AuthRepository
import com.github.ephelsa.mycareer.delivery.location.LocationRepository
import com.github.ephelsa.mycareer.usecase.auth.LoginUseCase
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object UseCasesProviders {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ) = LoginUseCase(authRepository)

    @Provides
    fun provideRegisterAUserUseCase(
        authRepository: AuthRepository
    ) = RegisterAUserUseCase(authRepository)

    @Provides
    fun provideCountriesUseCase(
        locationRepository: LocationRepository
    ) = CountriesUseCase(locationRepository)

    @Provides
    fun provideDepartmentsByCountryUseCase(
        locationRepository: LocationRepository
    ) = DepartmentsByCountryUseCase(locationRepository)

    @Provides
    fun provideMunicipalitiesByCountryAndDepartmentUseCase(
        locationRepository: LocationRepository
    ) = MunicipalitiesByCountryAndDepartmentUseCase(locationRepository)
}
