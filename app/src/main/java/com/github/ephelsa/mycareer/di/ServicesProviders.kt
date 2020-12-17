package com.github.ephelsa.mycareer.di

import com.github.ephelsa.mycareer.delivery.auth.remote.AuthService
import com.github.ephelsa.mycareer.delivery.documenttype.remote.DocumentTypeService
import com.github.ephelsa.mycareer.delivery.institutiontype.remote.InstitutionTypeService
import com.github.ephelsa.mycareer.delivery.location.remote.LocationService
import com.github.ephelsa.mycareer.delivery.studylevel.remote.StudyLevelService
import com.github.ephelsa.mycareer.delivery.survey.remote.SurveyService
import com.github.ephelsa.mycareer.delivery.user.remote.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
object ServicesProviders {

    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    fun provideLocationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)

    @Provides
    fun provideDocumentTypeService(retrofit: Retrofit): DocumentTypeService =
        retrofit.create(DocumentTypeService::class.java)

    @Provides
    fun provideInstitutionTypeService(retrofit: Retrofit): InstitutionTypeService =
        retrofit.create(InstitutionTypeService::class.java)

    @Provides
    fun provideStudyLevelService(retrofit: Retrofit): StudyLevelService =
        retrofit.create(StudyLevelService::class.java)

    @Provides
    fun provideSurveyService(retrofit: Retrofit): SurveyService =
        retrofit.create(SurveyService::class.java)

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}
