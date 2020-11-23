package com.github.ephelsa.mycareer.delivery.location.remote

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.google.gson.annotations.SerializedName

data class CountryJSON(
    @SerializedName("iso_code") val code: String,
    @SerializedName("name") val name: String
) : DomainMappable<CountryRemote> {
    override fun toDomain(): CountryRemote = CountryRemote(code, name)
}
