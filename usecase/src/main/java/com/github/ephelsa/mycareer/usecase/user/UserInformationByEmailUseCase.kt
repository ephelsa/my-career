package com.github.ephelsa.mycareer.usecase.user

import com.github.ephelsa.mycareer.data.user.UserRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.flow

class UserInformationByEmailUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String) = flow {
        emit(ResourceRemote.Loading())
        emit(userRepository.informationByEmail(email))
    }
}
