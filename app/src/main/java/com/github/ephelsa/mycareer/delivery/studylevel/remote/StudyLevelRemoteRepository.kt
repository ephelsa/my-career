package com.github.ephelsa.mycareer.delivery.studylevel.remote

import com.github.ephelsa.mycareer.data.studylevel.StudyLevelRemoteDataSource
import com.github.ephelsa.mycareer.delivery.shared.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperRemoteHandler
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StudyLevelRemoteRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val studyLevelService: StudyLevelService
) : StudyLevelRemoteDataSource {
    override suspend fun studyLevels(): ResourceRemote<List<StudyLevelRemote>> =
        withContext(dispatcher) {
            try {
                val responseJSON = studyLevelService.studyLevels()
                WrapperRemoteHandler.handleSuccess(responseJSON.toDomain())
            } catch (e: Exception) {
                WrapperRemoteHandler.handleError<List<StudyLevelRemote>>(e)
            }
        }
}
