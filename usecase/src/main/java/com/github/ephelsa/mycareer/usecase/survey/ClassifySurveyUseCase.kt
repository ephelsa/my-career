package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyClassifyRemote
import com.github.ephelsa.mycareer.usecase.user.RetrieveUserInformationUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class ClassifySurveyUseCase(
    private val surveyRepository: SurveyRepository,
    private val retrieveUserInformationUseCase: RetrieveUserInformationUseCase
) {
    operator fun invoke(surveyID: String, resolveAttempt: Int) = flow {
        emit(ResourceRemote.Loading())
        retrieveUserInformationUseCase()
            .collect {
                if (it is ResourceLocal.Success) {
                    val surveyClassifyRemote = SurveyClassifyRemote(
                        documentType = it.data.documentType,
                        document = it.data.document,
                        email = it.data.email,
                        surveyId = surveyID.toInt(),
                        resolveAttempt = resolveAttempt
                    )
                    emit(surveyRepository.classifyAnswers(surveyClassifyRemote))
                } else if (it is ResourceLocal.Error) {
                    val err = ErrorRemote(it.error.toString(), it.error.toString())
                    emit(ResourceRemote.Error(StatusRemote.UnexpectedError, err))
                }
            }
    }
}
