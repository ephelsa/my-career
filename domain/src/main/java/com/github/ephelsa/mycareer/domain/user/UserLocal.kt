package com.github.ephelsa.mycareer.domain.user

import com.github.ephelsa.mycareer.domain.shared.LocalMapper

data class UserLocal(
    val firstName: String,
    val secondName: String?,
    val firstSurname: String,
    val secondSurname: String?,
    val email: String,
    val documentType: String,
    val document: String,
) : LocalMapper<UserRemote> {
    override fun remoteTransform(): UserRemote = UserRemote(
        firstName,
        secondName,
        firstSurname,
        secondSurname,
        email,
        documentType,
        document
    )
}
