package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.google.gson.annotations.SerializedName

data class SurveyClassifyJSON(
    @SerializedName("document_type_code") val documentType: String,
    @SerializedName("document") val document: String,
    @SerializedName("email") val email: String,
    @SerializedName("survey") val surveyId: Int,
    @SerializedName("resolve_attempt") val resolveAttempt: Int
)
