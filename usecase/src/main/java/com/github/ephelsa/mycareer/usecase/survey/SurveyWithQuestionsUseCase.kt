package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SurveyWithQuestionsUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke(surveyCode: Int): Flow<ResourceRemote<SurveyWithQuestionsRemote>> = flow {
        emit(ResourceRemote.Loading())
        emit(surveyRepository.surveyWithQuestions(surveyCode))
    }
}
