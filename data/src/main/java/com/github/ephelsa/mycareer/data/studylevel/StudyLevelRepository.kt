package com.github.ephelsa.mycareer.data.studylevel

class StudyLevelRepository(
    private val studyLevelRemoteDataSource: StudyLevelRemoteDataSource
) {
    suspend fun studyLevels() = studyLevelRemoteDataSource.studyLevels()
}
