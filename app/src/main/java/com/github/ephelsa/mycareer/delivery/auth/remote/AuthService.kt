package com.github.ephelsa.mycareer.delivery.auth.remote

import com.github.ephelsa.mycareer.delivery.auth.pojo.AuthCredentialJSON
import com.github.ephelsa.mycareer.delivery.auth.pojo.RegistryJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedResponseJSON
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
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
