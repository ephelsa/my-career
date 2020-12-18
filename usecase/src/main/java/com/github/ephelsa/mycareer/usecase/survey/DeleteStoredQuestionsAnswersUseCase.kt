package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import kotlinx.coroutines.flow.flow

class DeleteStoredQuestionsAnswersUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke() = flow {
        emit(ResourceLocal.Loading())
        emit(surveyRepository.deleteQuestionsAnswers())
    }
}
