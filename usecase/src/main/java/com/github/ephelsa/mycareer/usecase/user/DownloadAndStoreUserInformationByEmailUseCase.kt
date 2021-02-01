package com.github.ephelsa.mycareer.usecase.user

import com.github.ephelsa.mycareer.data.user.UserRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DownloadAndStoreUserInformationByEmailUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(email: String): Flow<ResourceLocal<Unit>> = flow {
        emit(ResourceLocal.Loading())
        val response = userRepository.informationByEmail(email)

        if (response is ResourceRemote.Success) {
            emit(userRepository.storeInformation(response.data.localTransform()))
        } else if (response is ResourceRemote.Error) {
            emit(ResourceLocal.Error(Exception(response.error.message)))
        }
    }
}
