package com.github.ephelsa.mycareer.data.survey

import com.github.ephelsa.mycareer.domain.survey.*
import com.github.ephelsa.mycareer.domain.user.UserRemote

class SurveyRepository(
    private val surveyRemoteDataSource: SurveyRemoteDataSource,
    private val surveyLocalDataSource: SurveyLocalDataSource
) {
    suspend fun surveys(userRemote: UserRemote) = surveyRemoteDataSource.surveys(userRemote)

    suspend fun surveyWithQuestions(surveyCode: Int) =
        surveyRemoteDataSource.surveyWithQuestions(surveyCode)

    suspend fun sendUserAnswers(listUserRemote: List<UserAnswerRemote>) =
        surveyRemoteDataSource.sendUserAnswers(listUserRemote)

    suspend fun classifyAnswers(surveyClassifyRemote: SurveyClassifyRemote) =
        surveyRemoteDataSource.classifyAnswers(surveyClassifyRemote)

    suspend fun deleteQuestions() = surveyLocalDataSource.deleteQuestions()

    suspend fun storeQuestions(questionsLocal: List<QuestionLocal>) =
        surveyLocalDataSource.storeQuestions(questionsLocal)

    suspend fun deleteQuestionsAnswers() = surveyLocalDataSource.deleteQuestionsAnswers()

    suspend fun storeQuestionsAnswers(questionsAnswersLocal: List<QuestionAnswerLocal>) =
        surveyLocalDataSource.storeQuestionsAnswers(questionsAnswersLocal)

    suspend fun getQuestionsAndAnswers() = surveyLocalDataSource.getQuestionsAndAnswers()

    suspend fun storeUserAnswer(userAnswerLocal: UserAnswerLocal) =
        surveyLocalDataSource.storeUserAnswer(userAnswerLocal)

    suspend fun getUserAnswers(
        surveyId: String,
        resolveAttempt: Int
    ) = surveyLocalDataSource.getUserAnswers(surveyId, resolveAttempt)
}
