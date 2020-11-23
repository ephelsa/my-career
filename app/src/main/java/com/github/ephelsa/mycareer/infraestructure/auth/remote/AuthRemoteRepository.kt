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
    private val wrapperRemoteHandler: WrapperRemoteHandler,
    private val authService: AuthService,
) : AuthRemoteDataSource {

    override fun newUser(registryRemote: RegistryRemote): Flow<ResourceRemote<AuthCredentialRemote>> =
        flow {
            try {
                val response = authService.registry(registryRemote.toDelivery())
                emit(wrapperRemoteHandler.handleSuccess(response.toDomain()))
            } catch (e: Exception) {
                emit(wrapperRemoteHandler.handleError<AuthCredentialRemote>(e))
            }
        }
}
