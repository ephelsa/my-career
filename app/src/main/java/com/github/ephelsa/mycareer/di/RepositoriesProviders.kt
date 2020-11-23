package com.github.ephelsa.mycareer.di

import com.github.ephelsa.mycareer.delivery.auth.AuthRepository
import com.github.ephelsa.mycareer.delivery.auth.remote.AuthRemoteRepository
import com.github.ephelsa.mycareer.delivery.location.LocationRepository
import com.github.ephelsa.mycareer.delivery.location.remote.LocationRemoteRepository
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
}
