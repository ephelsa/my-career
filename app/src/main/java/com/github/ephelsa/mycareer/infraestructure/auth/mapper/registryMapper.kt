package com.github.ephelsa.mycareer.infraestructure.auth.mapper

import com.github.ephelsa.mycareer.domain.auth.RegistryRemote
import com.github.ephelsa.mycareer.infraestructure.auth.remote.RegistryJSON

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
