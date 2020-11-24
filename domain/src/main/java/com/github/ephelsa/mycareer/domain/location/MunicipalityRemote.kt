package com.github.ephelsa.mycareer.domain.location

data class MunicipalityRemote(
    val countryCode: String,
    val departmentCode: String,
    val municipalityCode: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
