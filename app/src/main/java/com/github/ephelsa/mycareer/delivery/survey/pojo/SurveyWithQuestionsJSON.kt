package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import com.google.gson.annotations.SerializedName

data class SurveyWithQuestionsJSON(
    @SerializedName("survey_id") val id: Int,
    @SerializedName("survey_name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("questions") val questionsWithAnswers: List<QuestionWithAnswersJSON>
) : DomainMappable<SurveyWithQuestionsRemote> {
    override fun toDomain(): SurveyWithQuestionsRemote = SurveyWithQuestionsRemote(
        id = id,
        name = name,
        description = description,
        questionsWithAnswers = questionsWithAnswers.map { it.toDomain() }
    )
}
