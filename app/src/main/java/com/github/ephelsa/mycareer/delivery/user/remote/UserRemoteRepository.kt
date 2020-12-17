package com.github.ephelsa.mycareer.delivery.user.remote

import com.github.ephelsa.mycareer.data.user.UserRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.user.UserRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userService: UserService
) : UserRemoteDataSource {
    override suspend fun informationByEmail(email: String): ResourceRemote<UserRemote> =
        withContext(dispatcher) {
            try {
                val responseJSON = userService.userInformation(email)
                WrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                WrapperRemoteHandler.handleError<UserRemote>(e)
            }
        }
}
