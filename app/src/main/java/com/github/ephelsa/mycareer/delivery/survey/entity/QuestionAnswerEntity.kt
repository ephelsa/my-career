package com.github.ephelsa.mycareer.delivery.survey.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal

@Entity(
    tableName = QuestionAnswerEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = QuestionEntity::class,
            parentColumns = [QuestionEntity.QUESTION_ID],
            childColumns = [QuestionAnswerEntity.QUESTION_ID]
        )
    ],
    primaryKeys = [QuestionAnswerEntity.QUESTION_ID, QuestionAnswerEntity.QUESTION_ANSWER_ID]
)
data class QuestionAnswerEntity(
    @ColumnInfo(name = QUESTION_ID) val questionId: Int,
    @ColumnInfo(name = QUESTION_ANSWER_ID) val id: Int,
    @ColumnInfo(name = ANSWER) val answer: String
) : DomainMappable<QuestionAnswerLocal> {
    companion object {
        const val TABLE_NAME = "question_answer"

        const val QUESTION_ID = "question_id"
        const val QUESTION_ANSWER_ID = "id"
        const val ANSWER = "answer"
    }

    override fun toDomain(): QuestionAnswerLocal = QuestionAnswerLocal(questionId, id, answer)
}
