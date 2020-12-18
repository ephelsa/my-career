package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import kotlinx.coroutines.flow.flow

class DeleteStoredSessions(
    private val authRepository: AuthRepository
) {
    operator fun invoke() = flow {
        emit(ResourceLocal.Loading())
        emit(authRepository.deleteStoredSessions())
    }
}