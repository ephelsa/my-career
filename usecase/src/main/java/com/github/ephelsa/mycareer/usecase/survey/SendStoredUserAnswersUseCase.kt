package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.UserAnswerRemote
import com.github.ephelsa.mycareer.domain.user.UserLocal
import com.github.ephelsa.mycareer.usecase.user.RetrieveUserInformationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class SendStoredUserAnswersUseCase(
    private val surveyRepository: SurveyRepository,
    private val retrieveUserInformationUseCase: RetrieveUserInformationUseCase
) {
    operator fun invoke(
        surveyId: String,
        resolveAttempt: Int
    ): Flow<ResourceRemote<StatusRemote>> = flow {
        emit(ResourceRemote.Loading())
        val answersLocal: ResourceLocal<List<UserAnswerLocal>> =
            surveyRepository.getUserAnswers(surveyId, resolveAttempt)

        if (answersLocal is ResourceLocal.Success) {
            retrieveUserInformationUseCase()
                .catch { emit(ResourceLocal.Error(Exception(it.localizedMessage))) }
                .collect { local ->
                    if (local is ResourceLocal.Success) {
                        val data = createUserAnswerRemoteList(local.data, answersLocal.data)
                        emit(surveyRepository.sendUserAnswers(data))
                    } else if (local is ResourceLocal.Error) {
                        val error = ErrorRemote(local.error.toString(), local.error.toString())
                        emit(ResourceRemote.Error(StatusRemote.UnexpectedError, error))
                    }
                }
        } else if (answersLocal is ResourceLocal.Error) {
            val error = ErrorRemote(answersLocal.error.toString(), answersLocal.error.toString())
            emit(ResourceRemote.Error(StatusRemote.UnexpectedError, error))
        }
    }

    private fun createUserAnswerRemoteList(
        userLocal: UserLocal,
        userAnswerLocal: List<UserAnswerLocal>
    ): List<UserAnswerRemote> = userAnswerLocal.map {
        UserAnswerRemote(
            email = userLocal.email,
            documentType = userLocal.documentType,
            document = userLocal.document,
            answer = it.answer,
            questionId = it.questionID.toInt(),
            surveyId = it.surveyID.toInt(),
            resolveAttempt = it.resolveAttempt
        )
    }
}
