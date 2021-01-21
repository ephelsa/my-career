package com.github.ephelsa.mycareer.delivery.survey.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAndQuestionsAnswersRelation
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAnswerEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionEntity

@Dao
interface SurveyDao {
    @Query("DELETE FROM question")
    suspend fun deleteQuestions()

    @Insert
    suspend fun insertQuestion(vararg questionEntity: QuestionEntity)

    @Query("DELETE FROM question_answer")
    suspend fun deleteQuestionAnswers()

    @Insert
    suspend fun insertQuestionAnswer(vararg questionAnswerEntity: QuestionAnswerEntity)

    @Transaction
    @Query("SELECT * FROM question WHERE id IN (SELECT DISTINCT(question.id) FROM question_answer)")
    suspend fun getQuestionsAndQuestionAnswers(): List<QuestionAndQuestionsAnswersRelation>
}
