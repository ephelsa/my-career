package com.github.ephelsa.mycareer.delivery.auth.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionEntity(
    @PrimaryKey val email: String,
    val password: String,
    val token: String
)
