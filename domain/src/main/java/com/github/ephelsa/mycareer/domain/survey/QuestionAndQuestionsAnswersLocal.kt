package com.github.ephelsa.mycareer.domain.survey

data class QuestionAndQuestionsAnswersLocal(
    val questionId: Int,
    val question: String,
    val type: QuestionTypeLocal,
    val answers: List<QuestionAnswerLocal?>
)
