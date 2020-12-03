package com.github.ephelsa.mycareer.di

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.data.documenttype.DocumentTypeRepository
import com.github.ephelsa.mycareer.data.institutiontype.InstitutionTypeRepository
import com.github.ephelsa.mycareer.data.location.LocationRepository
import com.github.ephelsa.mycareer.data.studylevel.StudyLevelRepository
import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.delivery.auth.remote.AuthRemoteRepository
import com.github.ephelsa.mycareer.delivery.documenttype.remote.DocumentTypeRemoteRepository
import com.github.ephelsa.mycareer.delivery.institutiontype.remote.InstitutionTypeRemoteRepository
import com.github.ephelsa.mycareer.delivery.location.remote.LocationRemoteRepository
import com.github.ephelsa.mycareer.delivery.studylevel.remote.StudyLevelRemoteRepository
import com.github.ephelsa.mycareer.delivery.survey.remote.SurveyRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object RepositoriesProviders {

    @Provides
    fun provideAuthRepository(
        authRemoteRepository: AuthRemoteRepository
    ): AuthRepository = AuthRepository(authRemoteRepository)

    @Provides
    fun provideLocationRepository(
        locationRemoteRepository: LocationRemoteRepository
    ): LocationRepository = LocationRepository(locationRemoteRepository)

    @Provides
    fun provideDocumentTypeRepository(
        documentTypeRemoteRepository: DocumentTypeRemoteRepository
    ): DocumentTypeRepository = DocumentTypeRepository(documentTypeRemoteRepository)

    @Provides
    fun provideInstitutionTypeRepository(
        institutionTypeRemoteRepository: InstitutionTypeRemoteRepository
    ): InstitutionTypeRepository = InstitutionTypeRepository(institutionTypeRemoteRepository)

    @Provides
    fun provideStudyLevelRepository(
        studyLevelRemoteRepository: StudyLevelRemoteRepository
    ): StudyLevelRepository = StudyLevelRepository(studyLevelRemoteRepository)

    @Provides
    fun providesSurveyRepository(
        surveyRemoteRepository: SurveyRemoteRepository
    ): SurveyRepository = SurveyRepository(surveyRemoteRepository)
}
