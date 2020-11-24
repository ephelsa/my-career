package com.github.ephelsa.mycareer.di

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.data.documenttype.DocumentTypeRepository
import com.github.ephelsa.mycareer.data.institutiontype.InstitutionTypeRepository
import com.github.ephelsa.mycareer.data.location.LocationRepository
import com.github.ephelsa.mycareer.data.studylevel.StudyLevelRepository
import com.github.ephelsa.mycareer.usecase.auth.LoginUseCase
import com.github.ephelsa.mycareer.usecase.auth.RegisterAUserUseCase
import com.github.ephelsa.mycareer.usecase.documenttype.DocumentTypesUseCase
import com.github.ephelsa.mycareer.usecase.institutiontype.InstitutionTypesUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import com.github.ephelsa.mycareer.usecase.studylevel.StudyLevelsUseCase
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

    @Provides
    fun provideDocumentTypesUseCase(
        documentTypeRepository: DocumentTypeRepository
    ) = DocumentTypesUseCase(documentTypeRepository)

    @Provides
    fun provideInstitutionsTypesUseCase(
        institutionTypeRepository: InstitutionTypeRepository
    ) = InstitutionTypesUseCase(institutionTypeRepository)

    @Provides
    fun provideStudyLevelsUseCase(
        studyLevelRepository: StudyLevelRepository
    ) = StudyLevelsUseCase(studyLevelRepository)
}
