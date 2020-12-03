package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.google.gson.annotations.SerializedName

data class SurveyJSON(
    @SerializedName("survey_id") val id: Int,
    @SerializedName("survey_name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("is_active") val isActive: Boolean
) : DomainMappable<SurveyRemote> {
    override fun toDomain(): SurveyRemote = SurveyRemote(id, name, description, isActive)
}
