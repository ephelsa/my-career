package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal
import kotlinx.coroutines.flow.flow

class StoreSurveyQuestionsUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke(questionsLocal: List<QuestionLocal>) = flow {
        emit(ResourceLocal.Loading())
        emit(surveyRepository.storeQuestions(questionsLocal))
    }
}