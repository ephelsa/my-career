package com.github.ephelsa.mycareer.delivery.user.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.user.UserRemote
import com.google.gson.annotations.SerializedName

data class UserJSON(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("second_name") val secondName: String?,
    @SerializedName("first_surname") val firstSurname: String,
    @SerializedName("second_surname") val secondSurname: String?,
    @SerializedName("email") val email: String,
    @SerializedName("document_type_code") val documentType: String,
    @SerializedName("document") val document: String
) : DomainMappable<UserRemote> {
    override fun toDomain(): UserRemote =
        UserRemote(
            firstName,
            secondName,
            firstSurname,
            secondSurname,
            email,
            documentType,
            document
        )
}
