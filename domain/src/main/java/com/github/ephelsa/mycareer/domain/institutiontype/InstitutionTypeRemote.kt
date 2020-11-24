package com.github.ephelsa.mycareer.domain.institutiontype

data class InstitutionTypeRemote(
    val id: Int,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
