package com.github.ephelsa.mycareer.delivery.survey.remote

import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedResponseJSON
import com.github.ephelsa.mycareer.delivery.survey.pojo.SurveyJSON
import com.github.ephelsa.mycareer.delivery.survey.pojo.SurveyWithQuestionsJSON
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface SurveyService {
    @GET("survey/")
    suspend fun surveys(): WrappedListResponseJSON<SurveyRemote, SurveyJSON>

    @GET("survey/{id}/questions-with-answers")
    suspend fun surveyWithQuestions(
        @Path("id") id: Int
    ): WrappedResponseJSON<SurveyWithQuestionsRemote, SurveyWithQuestionsJSON>
}
