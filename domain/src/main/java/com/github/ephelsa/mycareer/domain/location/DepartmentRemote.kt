package com.github.ephelsa.mycareer.domain.location

data class DepartmentRemote(
    val countryCode: String,
    val departmentCode: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
