package com.github.ephelsa.mycareer.delivery.auth.mapper

import com.github.ephelsa.mycareer.delivery.auth.entity.SessionEntity
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal

fun AuthCredentialLocal.toRoom() = SessionEntity(email, password, token)

fun SessionEntity.toDomain() = AuthCredentialLocal(email, password, token)
