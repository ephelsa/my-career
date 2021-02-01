package com.github.ephelsa.mycareer.data.survey

import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal

interface SurveyLocalDataSource {
    suspend fun deleteQuestions(): ResourceLocal<Unit>
    suspend fun storeQuestions(questionsLocal: List<QuestionLocal>): ResourceLocal<Unit>

    suspend fun deleteQuestionsAnswers(): ResourceLocal<Unit>
    suspend fun storeQuestionsAnswers(questionsAnswersLocal: List<QuestionAnswerLocal>): ResourceLocal<Unit>

    suspend fun getQuestionsAndAnswers(): ResourceLocal<List<QuestionAndQuestionsAnswersLocal>>

    suspend fun storeUserAnswer(userAnswerLocal: UserAnswerLocal): ResourceLocal<Unit>
}
