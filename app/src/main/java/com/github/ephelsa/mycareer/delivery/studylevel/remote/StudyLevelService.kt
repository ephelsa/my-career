package com.github.ephelsa.mycareer.delivery.studylevel.remote

import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import retrofit2.http.GET

interface StudyLevelService {
    @GET("study-level")
    suspend fun studyLevels(): WrappedListResponseJSON<StudyLevelRemote, StudyLevelJSON>
}
