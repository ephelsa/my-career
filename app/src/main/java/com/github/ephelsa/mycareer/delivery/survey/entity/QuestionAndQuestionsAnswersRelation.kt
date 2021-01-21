package com.github.ephelsa.mycareer.delivery.survey.entity

import androidx.room.Embedded
import androidx.room.Relation

data class QuestionAndQuestionsAnswersRelation(
    @Embedded
    val questionEntity: QuestionEntity,

    @Relation(
        parentColumn = QuestionEntity.QUESTION_ID,
        entityColumn = QuestionAnswerEntity.QUESTION_ID
    )
    val questionAnswerEntity: List<QuestionAnswerEntity?>
)
