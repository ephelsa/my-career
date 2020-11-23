package com.github.ephelsa.mycareer.infraestructure.auth.remote

import com.github.ephelsa.mycareer.data.auth.AuthRemoteDataSource
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.infraestructure.auth.mapper.toDelivery
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.toDomain
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrapperRemoteHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRemoteRepository(
    private val authService: AuthService,
) : AuthRemoteDataSource {
    private val wrapperRemoteHandler = WrapperRemoteHandler

    override fun newUser(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
        flow {
            emit(ResourceRemote.Loading())
            try {
                val responseJSON = authService.registry(registryRemote.toDelivery())
                emit(wrapperRemoteHandler.handleSuccess(responseJSON.toDomain()))
            } catch (e: Exception) {
                emit(wrapperRemoteHandler.handleError<AuthCredentialRemote>(e))
            }
        }

    override fun login(authCredentialRemote: AuthCredentialRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
        flow {
            emit(ResourceRemote.Loading())
            try {
                val responseJSON = authService.login(authCredentialRemote.toDelivery())
                emit(wrapperRemoteHandler.handleSuccess(responseJSON.toDomain()))
            } catch (e: Exception) {
                emit(wrapperRemoteHandler.handleError<AuthCredentialRemote>(e))
            }
        }
}
