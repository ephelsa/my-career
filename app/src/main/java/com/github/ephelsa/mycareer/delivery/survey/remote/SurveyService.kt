package com.github.ephelsa.mycareer.delivery.survey.remote

import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.delivery.survey.pojo.SurveyJSON
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import retrofit2.http.GET

interface SurveyService {
    @GET("survey/")
    suspend fun surveys(): WrappedListResponseJSON<SurveyRemote, SurveyJSON>
}
