package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class LoginUseCase(
    private val authRepository: AuthRepository,
    private val storeSessionUseCase: StoreSessionUseCase
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

                storeSessionUseCase(authCredentialLocal)
                    .catch {
                        emit(remoteLogin)
                    }
                    .collect {
                        if (it !is ResourceLocal.Loading) {
                            emit(remoteLogin)
                        }
                    }
            } else {
                emit(remoteLogin)
            }
        }
}
