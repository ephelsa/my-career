package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class StoreSessionUseCase(
    private val authRepository: AuthRepository,
    private val deleteStoredSessions: DeleteStoredSessions
) {
    operator fun invoke(authCredentialLocal: AuthCredentialLocal) = flow {
        emit(ResourceLocal.Loading())

        deleteStoredSessions()
            .catch {
                emit(authRepository.storeSession(authCredentialLocal))
            }
            .collect {
                if (it !is ResourceLocal.Loading) {
                    emit(authRepository.storeSession(authCredentialLocal))
                }
            }
    }
}
