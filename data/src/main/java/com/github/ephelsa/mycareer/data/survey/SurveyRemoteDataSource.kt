package com.github.ephelsa.mycareer.data.survey

import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import com.github.ephelsa.mycareer.domain.survey.UserAnswerRemote
import com.github.ephelsa.mycareer.domain.user.UserRemote

interface SurveyRemoteDataSource {
    suspend fun surveys(userRemote: UserRemote): ResourceRemote<List<SurveyRemote>>
    suspend fun surveyWithQuestions(surveyCode: Int): ResourceRemote<SurveyWithQuestionsRemote>

    suspend fun sendUserAnswers(listUserRemote: List<UserAnswerRemote>): ResourceRemote<StatusRemote>
}
