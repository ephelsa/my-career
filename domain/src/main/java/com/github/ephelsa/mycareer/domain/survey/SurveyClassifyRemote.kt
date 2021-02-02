package com.github.ephelsa.mycareer.domain.survey

data class SurveyClassifyRemote(
    val documentType: String,
    val document: String,
    val email: String,
    val surveyId: Int,
    val resolveAttempt: Int
)
