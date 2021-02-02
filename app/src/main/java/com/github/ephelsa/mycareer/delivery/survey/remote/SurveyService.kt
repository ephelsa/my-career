package com.github.ephelsa.mycareer.delivery.survey.remote

import com.github.ephelsa.mycareer.delivery.shared.remote.json.StatusResponseJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedResponseJSON
import com.github.ephelsa.mycareer.delivery.survey.pojo.*
import com.github.ephelsa.mycareer.delivery.user.pojo.UserJSON
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.survey.ClassificationRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SurveyService {
    @POST("survey/by-user")
    suspend fun surveys(
        @Body userJSON: UserJSON
    ): WrappedListResponseJSON<SurveyRemote, SurveyJSON>

    @GET("survey/{id}/questions-with-answers")
    suspend fun surveyWithQuestions(
        @Path("id") id: Int
    ): WrappedResponseJSON<SurveyWithQuestionsRemote, SurveyWithQuestionsJSON>

    @POST("survey/bulk-answers")
    suspend fun sendBulkUserAnswers(
        @Body listUserAnswerJSON: List<UserAnswerJSON>
    ): WrappedResponseJSON<StatusRemote, StatusResponseJSON>

    @POST("survey/classify")
    suspend fun classifySurvey(
        @Body surveyClassifyJSON: SurveyClassifyJSON
    ): WrappedListResponseJSON<ClassificationRemote, ClassificationJSON>
}
