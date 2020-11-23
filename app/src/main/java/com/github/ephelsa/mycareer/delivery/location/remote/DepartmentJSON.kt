package com.github.ephelsa.mycareer.delivery.location.remote

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.location.DepartmentRemote
import com.google.gson.annotations.SerializedName

data class DepartmentJSON(
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("department_code") val departmentCode: String,
    @SerializedName("name") val name: String
) : DomainMappable<DepartmentRemote> {

    override fun toDomain(): DepartmentRemote = DepartmentRemote(countryCode, departmentCode, name)
}
