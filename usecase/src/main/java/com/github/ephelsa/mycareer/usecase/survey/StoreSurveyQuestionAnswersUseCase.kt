package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import kotlinx.coroutines.flow.flow

class StoreSurveyQuestionAnswersUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke(questionsAnswersLocal: List<QuestionAnswerLocal>) = flow {
        emit(ResourceLocal.Loading())
        emit(surveyRepository.storeQuestionsAnswers(questionsAnswersLocal))
    }
}
