package com.github.ephelsa.mycareer.domain.survey

data class UserAnswerRemote(
    val email: String,
    val documentType: String,
    val document: String,
    val surveyId: Int,
    val questionId: Int,
    val answer: String?
)
