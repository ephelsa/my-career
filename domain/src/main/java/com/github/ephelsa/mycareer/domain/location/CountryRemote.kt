package com.github.ephelsa.mycareer.domain.location

data class CountryRemote(
    val code: String,
    val name: String
) {
    override fun toString(): String {
        return name
    }
}
