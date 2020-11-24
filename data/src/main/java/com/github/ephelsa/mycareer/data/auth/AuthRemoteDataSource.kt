package com.github.ephelsa.mycareer.data.auth

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote

interface AuthRemoteDataSource {
    suspend fun newUser(registryRemote: RegistryRemote): ResourceRemote<AuthCredentialRemote>

    suspend fun login(authCredentialRemote: AuthCredentialRemote): ResourceRemote<AuthCredentialRemote>
}
