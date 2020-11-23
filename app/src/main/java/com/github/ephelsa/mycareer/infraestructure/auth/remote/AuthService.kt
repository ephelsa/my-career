package com.github.ephelsa.mycareer.infraestructure.auth.remote

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrappedResponseJSON
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("auth/registry")
    suspend fun registry(
        @Body registryJSON: RegistryJSON,
    ): WrappedResponseJSON<AuthCredentialRemote, AuthCredentialJSON>

    @POST("auth/login")
    suspend fun login(
        @Body authCredentialJSON: AuthCredentialJSON
    ): WrappedResponseJSON<AuthCredentialRemote, AuthCredentialJSON>
}
