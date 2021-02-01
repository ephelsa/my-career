package com.github.ephelsa.mycareer.delivery.survey.local

import com.github.ephelsa.mycareer.data.survey.SurveyLocalDataSource
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler.handleError
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler.handleSuccess
import com.github.ephelsa.mycareer.delivery.survey.mapper.toDelivery
import com.github.ephelsa.mycareer.delivery.survey.mapper.toDomain
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurveyLocalRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val surveyDao: SurveyDao
) : SurveyLocalDataSource {
    override suspend fun deleteQuestions(): ResourceLocal<Unit> = withContext(dispatcher) {
        try {
            val operation = surveyDao.deleteQuestions()
            handleSuccess(operation)
        } catch (e: Exception) {
            handleError<Unit>(e)
        }
    }

    override suspend fun storeQuestions(questionsLocal: List<QuestionLocal>): ResourceLocal<Unit> =
        withContext(dispatcher) {
            try {
                val transformed = questionsLocal.toDelivery().toTypedArray()
                val operation = surveyDao.insertQuestion(*transformed)
                handleSuccess(operation)
            } catch (e: Exception) {
                handleError<Unit>(e)
            }
        }

    override suspend fun deleteQuestionsAnswers(): ResourceLocal<Unit> = withContext(dispatcher) {
        try {
            val operation = surveyDao.deleteQuestionAnswers()
            handleSuccess(operation)
        } catch (e: Exception) {
            handleError<Unit>(e)
        }
    }

    override suspend fun storeQuestionsAnswers(questionsAnswersLocal: List<QuestionAnswerLocal>): ResourceLocal<Unit> =
        withContext(dispatcher) {
            try {
                val transformed = questionsAnswersLocal.toDelivery().toTypedArray()
                val operation = surveyDao.insertQuestionAnswer(*transformed)
                handleSuccess(operation)
            } catch (e: Exception) {
                handleError<Unit>(e)
            }
        }

    override suspend fun getQuestionsAndAnswers(): ResourceLocal<List<QuestionAndQuestionsAnswersLocal>> =
        withContext(dispatcher) {
            try {
                val operation = surveyDao.getQuestionsAndQuestionAnswers()
                handleSuccess(operation.toDomain())
            } catch (e: Exception) {
                handleError<List<QuestionAndQuestionsAnswersLocal>>(e)
            }
        }
}
