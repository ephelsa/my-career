package com.github.ephelsa.mycareer.domain.auth

import com.github.ephelsa.mycareer.domain.shared.RemoteMapper

data class AuthCredentialRemote(
    val email: String?,
    val password: String?,
    val token: String? = null
) : RemoteMapper<AuthCredentialLocal> {
    override fun localTransform(): AuthCredentialLocal =
        AuthCredentialLocal(email.toString(), password.toString(), token.toString())
}
