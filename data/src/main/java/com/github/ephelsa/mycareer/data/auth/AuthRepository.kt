package com.github.ephelsa.mycareer.data.auth

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository(
    private val authRemoteDataSource: AuthRemoteDataSource
) {
    fun newUser(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>> = flow {
        emit(ResourceRemote.Loading())
        emit(authRemoteDataSource.newUser(registryRemote))
    }

    fun login(authCredentialRemote: AuthCredentialRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
        flow {
            emit(ResourceRemote.Loading())
            emit(authRemoteDataSource.login(authCredentialRemote))
        }
}
