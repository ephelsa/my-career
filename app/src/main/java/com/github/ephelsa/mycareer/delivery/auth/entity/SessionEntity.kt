package com.github.ephelsa.mycareer.delivery.auth.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal

@Entity(
    tableName = SessionEntity.TABLE_NAME,
    primaryKeys = [SessionEntity.EMAIL]
)
data class SessionEntity(
    @ColumnInfo(name = EMAIL) val email: String,
    @ColumnInfo(name = PASSWORD) val password: String,
    @ColumnInfo(name = TOKEN) val token: String
) : DomainMappable<AuthCredentialLocal> {
    companion object {
        const val TABLE_NAME = "session"

        const val EMAIL = "email"
        const val PASSWORD = "pass"
        const val TOKEN = "token"
    }

    override fun toDomain(): AuthCredentialLocal = AuthCredentialLocal(email, password, token)
}
