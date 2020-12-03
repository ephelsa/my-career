package com.github.ephelsa.mycareer.delivery.survey.remote

import com.github.ephelsa.mycareer.data.survey.SurveyRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SurveyRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val surveyService: SurveyService
) : SurveyRemoteDataSource {

    override suspend fun surveys(): ResourceRemote<List<SurveyRemote>> = withContext(dispatcher) {
        try {
            val responseJSON = surveyService.surveys()
            WrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
        } catch (e: Exception) {
            WrapperRemoteHandler.handleError<List<SurveyRemote>>(e)
        }
    }

    override suspend fun surveyWithQuestions(surveyCode: Int): ResourceRemote<SurveyWithQuestionsRemote> {
        TODO("Not yet implemented")
    }
}
