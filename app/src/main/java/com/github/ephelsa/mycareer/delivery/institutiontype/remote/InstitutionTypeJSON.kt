package com.github.ephelsa.mycareer.delivery.institutiontype.remote

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.institutiontype.InstitutionTypeRemote
import com.google.gson.annotations.SerializedName

data class InstitutionTypeJSON(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : DomainMappable<InstitutionTypeRemote> {
    override fun toDomain(): InstitutionTypeRemote = InstitutionTypeRemote(id, name)
}
