package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(authCredentialRemote: AuthCredentialRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
        flow {
            emit(ResourceRemote.Loading())

            val remoteLogin = authRepository.login(authCredentialRemote)
            if (remoteLogin is ResourceRemote.Success) {
                val authCredentialLocal = AuthCredentialLocal(
                    email = authCredentialRemote.email ?: "",
                    password = authCredentialRemote.password ?: "",
                    token = remoteLogin.data.token ?: ""
                )

                when (authRepository.storeSession(authCredentialLocal)) {
                    is ResourceLocal.Loading -> emit(ResourceRemote.Loading<AuthCredentialRemote>())
                    is ResourceLocal.Success, is ResourceLocal.Error -> emit(remoteLogin)
                    else -> {
                        // Nothing happens
                    }
                }
            } else {
                emit(remoteLogin)
            }
        }
}
