package com.github.ephelsa.mycareer.delivery.documenttype.remote

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.documenttype.DocumentTypeRemote
import com.google.gson.annotations.SerializedName

data class DocumentTypeJSON(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
) : DomainMappable<DocumentTypeRemote> {
    override fun toDomain(): DocumentTypeRemote = DocumentTypeRemote(id, name)
}
