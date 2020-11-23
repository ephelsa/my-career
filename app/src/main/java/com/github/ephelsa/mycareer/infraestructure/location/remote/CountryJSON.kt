package com.github.ephelsa.mycareer.infraestructure.location.remote

import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.google.gson.annotations.SerializedName

data class CountryJSON(
    @SerializedName("iso_code") val code: String,
    @SerializedName("name") val name: String
) : DomainMappable<CountryRemote> {
    override fun toDomain(): CountryRemote = CountryRemote(code, name)
}
