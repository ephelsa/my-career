package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.QuestionWithAnswersRemote
import com.google.gson.annotations.SerializedName

data class QuestionWithAnswersJSON(
    @SerializedName("question_id") val id: Int,
    @SerializedName("question") val question: String,
    @SerializedName("type") val type: QuestionTypeJSON,
    @SerializedName("answers") val answers: List<AnswerJSON>?
) : DomainMappable<QuestionWithAnswersRemote> {
    override fun toDomain(): QuestionWithAnswersRemote = QuestionWithAnswersRemote(
        id = id,
        question = question,
        type = type.toDomain(),
        answers = answers?.map { it.toDomain() }
    )
}
