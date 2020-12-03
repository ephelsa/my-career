package com.github.ephelsa.mycareer.delivery.auth.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.google.gson.annotations.SerializedName

data class RegistryJSON(
    @SerializedName("first_name") val firstName: String,
    @SerializedName("second_name") val secondName: String,
    @SerializedName("first_surname") val firstSurname: String,
    @SerializedName("second_surname") val secondSurname: String,
    @SerializedName("document_type") val documentType: String,
    @SerializedName("document") val document: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("department_code") val departmentCode: String,
    @SerializedName("municipality_code") val municipalityCode: String,
    @SerializedName("study_level") val studyLevelCode: Int,
    @SerializedName("institution_type") val institutionType: Int,
    @SerializedName("institution_name") val institutionName: String,
    @SerializedName("email") val emailAddress: String,
    @SerializedName("password") val password: String
) : DomainMappable<RegistryRemote> {

    override fun toDomain(): RegistryRemote = RegistryRemote(
        firstName,
        secondName,
        firstSurname,
        secondSurname,
        documentType,
        document,
        countryCode,
        departmentCode,
        municipalityCode,
        studyLevelCode,
        institutionType,
        institutionName,
        emailAddress,
        password
    )
}
