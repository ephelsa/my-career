package com.github.ephelsa.mycareer.usecase.auth

import com.github.ephelsa.mycareer.delivery.auth.AuthRepository
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow

class RegisterAUserUseCase(
    private val authRepository: AuthRepository
) {
    operator fun invoke(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
        authRepository.newUser(registryRemote)
}
