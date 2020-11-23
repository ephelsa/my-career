package com.github.ephelsa.mycareer.data.auth

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    fun newUser(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>>

    fun login(authCredentialRemote: AuthCredentialRemote): Flow<ResourceRemote<AuthCredentialRemote>>
}
