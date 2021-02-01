package com.github.ephelsa.mycareer.delivery.survey.remote

import com.github.ephelsa.mycareer.data.survey.SurveyRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler.handleError
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler.handleSuccess
import com.github.ephelsa.mycareer.delivery.user.mapper.toDelivery
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import com.github.ephelsa.mycareer.domain.user.UserRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurveyRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val surveyService: SurveyService
) : SurveyRemoteDataSource {

    override suspend fun surveys(userRemote: UserRemote): ResourceRemote<List<SurveyRemote>> = withContext(dispatcher) {
        try {
            val responseJSON = surveyService.surveys(userRemote.toDelivery())
            handleSuccess(responseJSON.toDomain())
        } catch (e: Exception) {
            handleError<List<SurveyRemote>>(e)
        }
    }

    override suspend fun surveyWithQuestions(surveyCode: Int): ResourceRemote<SurveyWithQuestionsRemote> =
        withContext(dispatcher) {
            try {
                val responseJSON = surveyService.surveyWithQuestions(surveyCode)
                handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                handleError<SurveyWithQuestionsRemote>(e)
            }
        }
}
