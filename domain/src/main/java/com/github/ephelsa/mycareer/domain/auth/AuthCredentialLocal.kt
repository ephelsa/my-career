package com.github.ephelsa.mycareer.domain.auth

import com.github.ephelsa.mycareer.domain.shared.LocalMapper

data class AuthCredentialLocal(
    val email: String,
    val password: String,
    val token: String,
) : LocalMapper<AuthCredentialRemote> {
    override fun remoteTransform(): AuthCredentialRemote = AuthCredentialRemote(
        email, password, token
    )
}
