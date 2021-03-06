package com.github.ephelsa.mycareer.domain.user

import com.github.ephelsa.mycareer.domain.shared.RemoteMapper

data class UserRemote(
    val firstName: String,
    val secondName: String?,
    val firstSurname: String,
    val secondSurname: String?,
    val email: String,
    val documentType: String,
    val document: String,
) : RemoteMapper<UserLocal> {
    override fun localTransform(): UserLocal = UserLocal(
        firstName,
        secondName,
        firstSurname,
        secondSurname,
        email,
        documentType,
        document
    )
}
