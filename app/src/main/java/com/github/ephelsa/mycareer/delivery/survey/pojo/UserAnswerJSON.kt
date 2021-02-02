package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.UserAnswerRemote
import com.google.gson.annotations.SerializedName

data class UserAnswerJSON(
    @SerializedName("email") val email: String,
    @SerializedName("document_type_code") val documentType: String,
    @SerializedName("document") val document: String,
    @SerializedName("survey") val surveyId: Int,
    @SerializedName("question") val questionId: Int,
    @SerializedName("answer") val answer: String?,
    @SerializedName("resolve_attempt") val resolveAttempt: Int
) : DomainMappable<UserAnswerRemote> {
    override fun toDomain(): UserAnswerRemote = UserAnswerRemote(
        email,
        documentType,
        document,
        surveyId,
        questionId,
        answer,
        resolveAttempt
    )
}
