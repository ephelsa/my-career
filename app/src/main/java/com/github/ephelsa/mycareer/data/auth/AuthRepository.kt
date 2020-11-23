package com.github.ephelsa.mycareer.data.auth

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote

class AuthRepository(
    private val authRemoteDataSource: AuthRemoteDataSource
) {
    fun newUser(registryRemote: RegistryRemote) =
        authRemoteDataSource.newUser(registryRemote)

    fun login(authCredentialRemote: AuthCredentialRemote) =
        authRemoteDataSource.login(authCredentialRemote)
}
