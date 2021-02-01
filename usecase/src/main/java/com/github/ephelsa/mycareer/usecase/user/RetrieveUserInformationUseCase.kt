package com.github.ephelsa.mycareer.usecase.user

import com.github.ephelsa.mycareer.data.user.UserRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import kotlinx.coroutines.flow.flow

class RetrieveUserInformationUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke() = flow {
        emit(ResourceLocal.Loading())
        emit(userRepository.retrieveInformation())
    }
}
