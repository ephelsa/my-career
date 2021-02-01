package com.github.ephelsa.mycareer.delivery.survey.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal

@Entity(
    tableName = UserAnswerEntity.TABLE_NAME,
    primaryKeys = [
        UserAnswerEntity.SURVEY_ID,
        UserAnswerEntity.QUESTION_ID,
        UserAnswerEntity.RESOLVE_ATTEMPT
    ]
)
data class UserAnswerEntity(
    @ColumnInfo(name = SURVEY_ID) val surveyID: String,
    @ColumnInfo(name = QUESTION_ID) val questionID: String,
    @ColumnInfo(name = ANSWER) val answer: String,
    @ColumnInfo(name = RESOLVE_ATTEMPT) val resolveAttempt: Int,
) : DomainMappable<UserAnswerLocal> {
    companion object {
        const val TABLE_NAME = "user_answer"

        const val SURVEY_ID = "survey_id"
        const val QUESTION_ID = "question_id"
        const val ANSWER = "answer"
        const val RESOLVE_ATTEMPT = "resolve_attempt"
    }

    override fun toDomain(): UserAnswerLocal = UserAnswerLocal(
        surveyID, questionID, answer, resolveAttempt
    )
}
