package com.github.ephelsa.mycareer.di

import android.content.Context
import com.github.ephelsa.mycareer.delivery.auth.local.AuthDao
import com.github.ephelsa.mycareer.delivery.shared.local.MyCareerDatabase
import com.github.ephelsa.mycareer.delivery.survey.local.SurveyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseProviders {

    @Singleton
    @Provides
    fun provideMyCareerDatabase(
        @ApplicationContext context: Context
    ): MyCareerDatabase = MyCareerDatabase.getInstance(context)

    @Provides
    fun provideAuthDao(
        myCareerDatabase: MyCareerDatabase
    ): AuthDao = myCareerDatabase.authDao()

    @Provides
    fun provideSurveyDao(
        myCareerDatabase: MyCareerDatabase
    ): SurveyDao = myCareerDatabase.surveyDao()
}
