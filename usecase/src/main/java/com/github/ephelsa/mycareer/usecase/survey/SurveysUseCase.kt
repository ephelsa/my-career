package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SurveysUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke(): Flow<ResourceRemote<List<SurveyRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(surveyRepository.surveys())
    }
}
