package com.github.ephelsa.mycareer.delivery.auth.mapper

import com.github.ephelsa.mycareer.delivery.auth.pojo.AuthCredentialJSON
import com.github.ephelsa.mycareer.delivery.auth.pojo.RegistryJSON
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.domain.auth.RegistryRemote

fun RegistryRemote.toDelivery() = RegistryJSON(
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

fun AuthCredentialRemote.toDelivery() = AuthCredentialJSON(email, password)
