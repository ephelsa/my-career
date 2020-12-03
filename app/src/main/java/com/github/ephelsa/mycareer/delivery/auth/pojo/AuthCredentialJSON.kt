package com.github.ephelsa.mycareer.delivery.auth.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.google.gson.annotations.SerializedName

data class AuthCredentialJSON(
    @SerializedName("email") val email: String?,
    @SerializedName("password") val password: String?
) : DomainMappable<AuthCredentialRemote> {

    override fun toDomain() = AuthCredentialRemote(email, password)
}
