package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal
import kotlinx.coroutines.flow.flow

class StoreUserAnswerUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke(userAnswerLocal: UserAnswerLocal) = flow {
        emit(ResourceLocal.Loading())
        emit(surveyRepository.storeUserAnswer(userAnswerLocal))
    }
}
