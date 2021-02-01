package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.user.UserRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SurveysUseCase(
    private val surveyRepository: SurveyRepository
) {
    operator fun invoke(userRemote: UserRemote): Flow<ResourceRemote<List<SurveyRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(surveyRepository.surveys(userRemote))
    }
}
