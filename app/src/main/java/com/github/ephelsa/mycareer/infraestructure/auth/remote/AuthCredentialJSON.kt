package com.github.ephelsa.mycareer.infraestructure.auth.remote

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthCredentialJSON(
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "password") val password: String?
) : DomainMappable<AuthCredentialRemote> {

    override fun toDomain() = AuthCredentialRemote(email, password)
}
