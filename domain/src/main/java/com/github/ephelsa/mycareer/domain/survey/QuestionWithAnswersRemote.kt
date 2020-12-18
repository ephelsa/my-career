package com.github.ephelsa.mycareer.domain.survey

data class QuestionWithAnswersRemote(
    val id: Int,
    val question: String,
    val type: QuestionTypeRemote,
    val answers: List<AnswerRemote>?
)
