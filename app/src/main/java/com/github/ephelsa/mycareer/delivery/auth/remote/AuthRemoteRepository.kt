package com.github.ephelsa.mycareer.delivery.auth.remote

import com.github.ephelsa.mycareer.delivery.auth.AuthRemoteDataSource
import com.github.ephelsa.mycareer.delivery.auth.mapper.toDelivery
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val authService: AuthService,
) : AuthRemoteDataSource {
    private val wrapperRemoteHandler = WrapperRemoteHandler

    override suspend fun newUser(registryRemote: RegistryRemote): ResourceRemote<AuthCredentialRemote> =
        withContext(dispatcher) {
            try {
                val responseJSON = authService.registry(registryRemote.toDelivery())
                wrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                wrapperRemoteHandler.handleError<AuthCredentialRemote>(e)
            }
        }

    override suspend fun login(authCredentialRemote: AuthCredentialRemote): ResourceRemote<AuthCredentialRemote> =
        withContext(dispatcher) {
            try {
                val responseJSON = authService.login(authCredentialRemote.toDelivery())
                wrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                wrapperRemoteHandler.handleError<AuthCredentialRemote>(e)
            }
        }
}
