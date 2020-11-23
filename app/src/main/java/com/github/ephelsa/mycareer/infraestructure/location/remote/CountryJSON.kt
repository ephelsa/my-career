package com.github.ephelsa.mycareer.infraestructure.location.remote

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryJSON(
    @field:Json(name = "iso_code") val code: String,
    @field:Json(name = "name") val name: String
) : DomainMappable<CountryRemote> {
    override fun toDomain(): CountryRemote = CountryRemote(code, name)
}
