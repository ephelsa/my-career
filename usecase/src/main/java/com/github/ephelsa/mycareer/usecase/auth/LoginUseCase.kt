package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.delivery.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote

class LoginUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(authCredentialRemote: AuthCredentialRemote) = authRepository.login(authCredentialRemote)
}
