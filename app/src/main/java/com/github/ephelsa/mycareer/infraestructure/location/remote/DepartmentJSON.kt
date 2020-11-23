package com.github.ephelsa.mycareer.infraestructure.location.remote

import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DepartmentJSON(
    @field:Json(name = "country_code") val countryCode: String,
    @field:Json(name = "department_code") val departmentCode: String,
    @field:Json(name = "name") val name: String
) : DomainMappable<DepartmentRemote> {

    override fun toDomain(): DepartmentRemote = DepartmentRemote(countryCode, departmentCode, name)
}
