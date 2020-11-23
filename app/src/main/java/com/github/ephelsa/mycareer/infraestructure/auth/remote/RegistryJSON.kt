package com.github.ephelsa.mycareer.infraestructure.auth.remote

import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegistryJSON(
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "second_name") val secondName: String,
    @field:Json(name = "first_surname") val firstSurname: String,
    @field:Json(name = "second_surname") val secondSurname: String,
    @field:Json(name = "document_type") val documentType: String,
    @field:Json(name = "document") val document: String,
    @field:Json(name = "country_code") val countryCode: String,
    @field:Json(name = "department_code") val departmentCode: String,
    @field:Json(name = "municipality_code") val municipalityCode: String,
    @field:Json(name = "study_level") val studyLevelCode: Int,
    @field:Json(name = "institution_type") val institutionType: Int,
    @field:Json(name = "institution_name") val institutionName: String,
    @field:Json(name = "email") val emailAddress: String,
    @field:Json(name = "password") val password: String
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
