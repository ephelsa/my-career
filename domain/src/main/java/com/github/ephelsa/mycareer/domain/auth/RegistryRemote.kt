package com.github.ephelsa.mycareer.domain.auth

data class RegistryRemote(
    val firstName: String,
    val secondName: String,
    val firstSurname: String,
    val secondSurname: String,
    val documentType: String,
    val document: String,
    val countryCode: String,
    val departmentCode: String,
    val municipalityCode: String,
    val studyLevelCode: Int,
    val institutionType: Int,
    val institutionName: String,
    val emailAddress: String,
    val password: String
)
