package com.github.ephelsa.mycareer.data.survey

import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.survey.*
import com.github.ephelsa.mycareer.domain.user.UserRemote

interface SurveyRemoteDataSource {
    suspend fun surveys(userRemote: UserRemote): ResourceRemote<List<SurveyRemote>>
    suspend fun surveyWithQuestions(surveyCode: Int): ResourceRemote<SurveyWithQuestionsRemote>

    suspend fun sendUserAnswers(listUserRemote: List<UserAnswerRemote>): ResourceRemote<StatusRemote>
    suspend fun classifyAnswers(surveyClassifyRemote: SurveyClassifyRemote): ResourceRemote<List<ClassificationRemote>>
}
