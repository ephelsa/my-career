package com.github.ephelsa.mycareer.infraestructure.auth.remote

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.google.gson.annotations.SerializedName

data class AuthCredentialJSON(
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?
) : DomainMappable<AuthCredentialRemote> {

    override fun toDomain() = AuthCredentialRemote(email, password)
}
