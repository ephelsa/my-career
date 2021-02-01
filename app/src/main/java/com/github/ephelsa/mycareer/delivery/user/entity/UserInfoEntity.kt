package com.github.ephelsa.mycareer.delivery.user.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.user.UserLocal

@Entity(
    tableName = UserInfoEntity.TABLE_NAME,
    primaryKeys = [UserInfoEntity.EMAIL, UserInfoEntity.DOCUMENT_TYPE_CODE, UserInfoEntity.DOCUMENT]
)
data class UserInfoEntity(
    @ColumnInfo(name = FIRST_NAME) val firstName: String,
    @ColumnInfo(name = SECOND_NAME) val secondName: String?,
    @ColumnInfo(name = FIRST_SURNAME) val firstSurname: String,
    @ColumnInfo(name = SECOND_SURNAME) val secondSurname: String?,
    @ColumnInfo(name = EMAIL) val email: String,
    @ColumnInfo(name = DOCUMENT_TYPE_CODE) val documentType: String,
    @ColumnInfo(name = DOCUMENT) val document: String
) : DomainMappable<UserLocal> {
    companion object {
        const val TABLE_NAME = "user_info"

        const val FIRST_NAME = "first_name"
        const val SECOND_NAME = "second_name"
        const val FIRST_SURNAME = "first_surname"
        const val SECOND_SURNAME = "second_surname"
        const val EMAIL = "email"
        const val DOCUMENT_TYPE_CODE = "document_type"
        const val DOCUMENT = "document"
    }

    override fun toDomain(): UserLocal = UserLocal(
        firstName,
        secondName,
        firstSurname,
        secondSurname,
        email,
        documentType,
        document
    )
}
