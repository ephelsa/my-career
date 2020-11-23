package com.github.ephelsa.mycareer.infraestructure.location.remote

import com.github.ephelsa.mycareer.domain.location.MunicipalityRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.google.gson.annotations.SerializedName

data class MunicipalityJSON(
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("department_code") val departmentCode: String,
    @SerializedName("municipality_code") val municipalityCode: String,
    @SerializedName("name") val name: String
) : DomainMappable<MunicipalityRemote> {

    override fun toDomain(): MunicipalityRemote =
        MunicipalityRemote(countryCode, departmentCode, municipalityCode, name)
}
