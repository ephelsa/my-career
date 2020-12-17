package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.flow

class RegisterAUserUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(registryRemote: RegistryRemote) = flow {
        emit(ResourceRemote.Loading())
        emit(authRepository.newUser(registryRemote))
    }
}
