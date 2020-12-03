package com.github.ephelsa.mycareer.data.survey

import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote

interface SurveyRemoteDataSource {
    suspend fun surveys(): ResourceRemote<List<SurveyRemote>>

    suspend fun surveyWithQuestions(surveyCode: Int): ResourceRemote<SurveyWithQuestionsRemote>
}
