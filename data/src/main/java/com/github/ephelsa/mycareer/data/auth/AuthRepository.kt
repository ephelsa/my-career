package com.github.ephelsa.mycareer.data.auth

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote

class AuthRepository(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) {
    suspend fun newUser(registryRemote: RegistryRemote) =
        authRemoteDataSource.newUser(registryRemote)

    suspend fun login(authCredentialRemote: AuthCredentialRemote) =
        authRemoteDataSource.login(authCredentialRemote)

    suspend fun storeSession(authCredentialLocal: AuthCredentialLocal) =
        authLocalDataSource.storeSession(authCredentialLocal)

    suspend fun getStoredSession() =
        authLocalDataSource.getStoredSession()
}
