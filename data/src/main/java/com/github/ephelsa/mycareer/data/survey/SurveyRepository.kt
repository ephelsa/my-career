package com.github.ephelsa.mycareer.data.survey

class SurveyRepository(
    private val surveyRemoteDataSource: SurveyRemoteDataSource
) {
    suspend fun surveys() = surveyRemoteDataSource.surveys()

    suspend fun surveyWithQuestions(surveyCode: Int) =
        surveyRemoteDataSource.surveyWithQuestions(surveyCode)
}
