package com.github.ephelsa.mycareer.delivery.survey.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.github.ephelsa.mycareer.domain.survey.QuestionTypeLocal

@Entity(
    tableName = QuestionEntity.TABLE_NAME,
    primaryKeys = [QuestionEntity.QUESTION_ID]
)
data class QuestionEntity(
    @ColumnInfo(name = QUESTION_ID) val id: Int,
    @ColumnInfo(name = QUESTION) val question: String,
    @ColumnInfo(name = TYPE) val type: QuestionTypeLocal,
) {
    companion object {
        const val TABLE_NAME = "question"

        const val QUESTION_ID = "id"
        const val QUESTION = "question"
        const val TYPE = "type"
    }
}
