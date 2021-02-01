package com.github.ephelsa.mycareer.delivery.survey.local

import androidx.room.*
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAndQuestionsAnswersRelation
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAnswerEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.UserAnswerEntity

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
    @Query("SELECT * FROM question q WHERE id IN (SELECT DISTINCT(q.id) FROM question_answer) ORDER BY q.id ASC")
    suspend fun getQuestionsAndQuestionAnswers(): List<QuestionAndQuestionsAnswersRelation>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserAnswer(userAnswerEntity: UserAnswerEntity)
}
