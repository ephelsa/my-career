package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.ClassificationRemote
import com.google.gson.annotations.SerializedName

data class ClassificationJSON(
    @SerializedName("name") val career: String,
    @SerializedName("percent") val percent: Float
) : DomainMappable<ClassificationRemote> {
    override fun toDomain(): ClassificationRemote = ClassificationRemote(
        career,
        percent
    )
}
