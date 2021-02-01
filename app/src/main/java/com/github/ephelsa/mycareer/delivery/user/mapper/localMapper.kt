package com.github.ephelsa.mycareer.delivery.user.mapper

import com.github.ephelsa.mycareer.delivery.user.entity.UserInfoEntity
import com.github.ephelsa.mycareer.delivery.user.pojo.UserJSON
import com.github.ephelsa.mycareer.domain.user.UserLocal
import com.github.ephelsa.mycareer.domain.user.UserRemote

fun UserLocal.toDelivery() = UserInfoEntity(
    firstName,
    secondName,
    firstSurname,
    secondSurname,
    email,
    documentType,
    document
)

fun UserRemote.toDelivery() = UserJSON(
    firstName,
    secondName,
    firstSurname,
    secondSurname,
    email,
    documentType,
    document
)
