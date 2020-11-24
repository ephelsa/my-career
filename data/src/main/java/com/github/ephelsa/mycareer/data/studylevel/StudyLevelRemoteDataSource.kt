package com.github.ephelsa.mycareer.data.studylevel

import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote

interface StudyLevelRemoteDataSource {
    suspend fun studyLevels(): ResourceRemote<List<StudyLevelRemote>>
}
