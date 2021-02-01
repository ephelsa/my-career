package com.github.ephelsa.mycareer.domain.survey

data class UserAnswerLocal(
    val surveyID: String,
    val questionID: String,
    val answer: String,
    val resolveAttempt: Int
)
