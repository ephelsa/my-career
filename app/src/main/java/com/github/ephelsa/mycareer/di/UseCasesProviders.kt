package com.github.ephelsa.mycareer.di

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.data.documenttype.DocumentTypeRepository
import com.github.ephelsa.mycareer.data.institutiontype.InstitutionTypeRepository
import com.github.ephelsa.mycareer.data.location.LocationRepository
import com.github.ephelsa.mycareer.data.studylevel.StudyLevelRepository
import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.data.user.UserRepository
import com.github.ephelsa.mycareer.usecase.auth.*
import com.github.ephelsa.mycareer.usecase.documenttype.DocumentTypesUseCase
import com.github.ephelsa.mycareer.usecase.institutiontype.InstitutionTypesUseCase
import com.github.ephelsa.mycareer.usecase.location.CountriesUseCase
import com.github.ephelsa.mycareer.usecase.location.DepartmentsByCountryUseCase
import com.github.ephelsa.mycareer.usecase.location.MunicipalitiesByCountryAndDepartmentUseCase
import com.github.ephelsa.mycareer.usecase.studylevel.StudyLevelsUseCase
import com.github.ephelsa.mycareer.usecase.survey.*
import com.github.ephelsa.mycareer.usecase.user.UserInformationByEmailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object UseCasesProviders {

    @Provides
    fun provideLoginUseCase(
        authRepository: AuthRepository,
        storeSessionUseCase: StoreSessionUseCase
    ) = LoginUseCase(authRepository, storeSessionUseCase)

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

    @Provides
    fun provideSurveysUseCase(
        surveyRepository: SurveyRepository
    ) = SurveysUseCase(surveyRepository)

    @Provides
    fun provideUserInformationByEmailUseCase(
        userRepository: UserRepository
    ) = UserInformationByEmailUseCase(userRepository)

    @Provides
    fun provideGetStoredCredentialsUseCase(
        authRepository: AuthRepository
    ) = GetStoredCredentialsUseCase(authRepository)

    @Provides
    fun provideStoreSessionUseCase(
        authRepository: AuthRepository,
        deleteStoredSessionsUseCase: DeleteStoredSessionsUseCase
    ) = StoreSessionUseCase(authRepository, deleteStoredSessionsUseCase)

    @Provides
    fun provideDeleteStoredSessions(
        authRepository: AuthRepository
    ) = DeleteStoredSessionsUseCase(authRepository)

    @Provides
    fun provideSurveyWithQuestionsUseCase(
        surveyRepository: SurveyRepository,
        surveyQuestionsAndAnswersUseCase: StoreSurveyQuestionsAndAnswersUseCase
    ) = SurveyWithQuestionsUseCase(surveyRepository, surveyQuestionsAndAnswersUseCase)

    @Provides
    fun provideStoreSurveyQuestionsUseCase(
        surveyRepository: SurveyRepository
    ) = StoreSurveyQuestionsUseCase(surveyRepository)

    @Provides
    fun provideStoreSurveyQuestionAnswersUseCase(
        surveyRepository: SurveyRepository
    ) = StoreSurveyQuestionAnswersUseCase(surveyRepository)

    @Provides
    fun provideStoreSurveyQuestionsAndAnswersUseCase(
        storeSurveyQuestionsUseCase: StoreSurveyQuestionsUseCase,
        storeSurveyQuestionAnswersUseCase: StoreSurveyQuestionAnswersUseCase,
        deleteStoredQuestionsAndAnswersUseCase: DeleteStoredQuestionsAndAnswersUseCase
    ) = StoreSurveyQuestionsAndAnswersUseCase(
        storeSurveyQuestionsUseCase,
        storeSurveyQuestionAnswersUseCase,
        deleteStoredQuestionsAndAnswersUseCase
    )

    @Provides
    fun provideDeleteStoredQuestionsUseCase(
        surveyRepository: SurveyRepository
    ) = DeleteStoredQuestionsUseCase(surveyRepository)

    @Provides
    fun provideDeleteStoredQuestionsAnswersUseCase(
        surveyRepository: SurveyRepository
    ) = DeleteStoredQuestionsAnswersUseCase(surveyRepository)

    @Provides
    fun provideDeleteStoredQuestionsAndAnswersUseCase(
        deleteStoredQuestionsUseCase: DeleteStoredQuestionsUseCase,
        deleteStoredQuestionsAnswersUseCase: DeleteStoredQuestionsAnswersUseCase
    ) = DeleteStoredQuestionsAndAnswersUseCase(
        deleteStoredQuestionsUseCase,
        deleteStoredQuestionsAnswersUseCase
    )

    @Provides
    fun provideQuestionsAndQuestionsAnswersUseCase(
        surveyRepository: SurveyRepository
    ) = StoredQuestionsAndQuestionAnswersUseCase(surveyRepository)
}
