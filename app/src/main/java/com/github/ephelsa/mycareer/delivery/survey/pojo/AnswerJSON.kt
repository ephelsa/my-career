package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.AnswerRemote
import com.google.gson.annotations.SerializedName

data class AnswerJSON(
    @SerializedName("id") val id: Int,
    @SerializedName("value") val value: String
) : DomainMappable<AnswerRemote> {
    override fun toDomain(): AnswerRemote = AnswerRemote(id, value)
}
