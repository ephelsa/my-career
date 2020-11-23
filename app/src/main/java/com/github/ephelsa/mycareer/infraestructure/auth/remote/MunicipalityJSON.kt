package com.github.ephelsa.mycareer.infraestructure.auth.remote

import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MunicipalityJSON(
    @field:Json(name = "country_code") val countryCode: String,
    @field:Json(name = "department_code") val departmentCode: String,
    @field:Json(name = "municipality_code") val municipalityCode: String,
    @field:Json(name = "name") val name: String
) : DomainMappable<MunicipalityRemote> {

    override fun toDomain(): MunicipalityRemote =
        MunicipalityRemote(countryCode, departmentCode, municipalityCode, name)
}
