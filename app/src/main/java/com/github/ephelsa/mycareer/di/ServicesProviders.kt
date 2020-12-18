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
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServicesProviders {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun provideLocationService(retrofit: Retrofit): LocationService =
        retrofit.create(LocationService::class.java)

    @Singleton
    @Provides
    fun provideDocumentTypeService(retrofit: Retrofit): DocumentTypeService =
        retrofit.create(DocumentTypeService::class.java)

    @Singleton
    @Provides
    fun provideInstitutionTypeService(retrofit: Retrofit): InstitutionTypeService =
        retrofit.create(InstitutionTypeService::class.java)

    @Singleton
    @Provides
    fun provideStudyLevelService(retrofit: Retrofit): StudyLevelService =
        retrofit.create(StudyLevelService::class.java)

    @Singleton
    @Provides
    fun provideSurveyService(retrofit: Retrofit): SurveyService =
        retrofit.create(SurveyService::class.java)

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}
