package com.github.ephelsa.mycareer.domain.survey

data class SurveyRemote(
    val id: Int,
    val name: String,
    val description: String?,
    val isActive: Boolean,
    val resolveAttempts: Int?,
    val questionsAnswered: Int?,
    val totalQuestions: Int
)
