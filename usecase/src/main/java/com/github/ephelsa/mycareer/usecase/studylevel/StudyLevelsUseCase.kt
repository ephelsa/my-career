package com.github.ephelsa.mycareer.usecase.studylevel

import com.github.ephelsa.mycareer.data.studylevel.StudyLevelRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.studylevel.StudyLevelRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StudyLevelsUseCase(
    private val studyLevelRepository: StudyLevelRepository
) {
    operator fun invoke(): Flow<ResourceRemote<List<StudyLevelRemote>>> = flow {
        emit(ResourceRemote.Loading())
        emit(studyLevelRepository.studyLevels())
    }
}
