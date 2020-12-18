package com.github.ephelsa.mycareer.delivery.auth.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = SessionEntity.TABLE_NAME,
    primaryKeys = [SessionEntity.EMAIL]
)
data class SessionEntity(
    @ColumnInfo(name = EMAIL) val email: String,
    @ColumnInfo(name = PASSWORD) val password: String,
    @ColumnInfo(name = TOKEN) val token: String
) {
    companion object {
        const val TABLE_NAME = "session"

        const val EMAIL = "email"
        const val PASSWORD = "pass"
        const val TOKEN = "token"
    }
}
