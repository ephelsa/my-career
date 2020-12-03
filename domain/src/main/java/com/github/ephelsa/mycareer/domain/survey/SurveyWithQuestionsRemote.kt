package com.github.ephelsa.mycareer.domain.survey

data class SurveyWithQuestionsRemote(
    val id: Int,
    val name: String,
    val description: String?,
    val questionsWithAnswersRemote: List<QuestionWithAnswersRemote>
)
