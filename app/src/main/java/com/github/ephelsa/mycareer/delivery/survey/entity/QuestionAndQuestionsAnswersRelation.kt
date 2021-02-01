package com.github.ephelsa.mycareer.delivery.survey.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal

data class QuestionAndQuestionsAnswersRelation(
    @Embedded
    val questionEntity: QuestionEntity,

    @Relation(
        parentColumn = QuestionEntity.QUESTION_ID,
        entityColumn = QuestionAnswerEntity.QUESTION_ID
    )
    val questionAnswerEntity: List<QuestionAnswerEntity?>
) : DomainMappable<QuestionAndQuestionsAnswersLocal> {

    override fun toDomain(): QuestionAndQuestionsAnswersLocal = QuestionAndQuestionsAnswersLocal(
        questionId = questionEntity.id,
        answers = questionAnswerEntity.map { it?.toDomain() },
        type = questionEntity.type,
        question = questionEntity.question
    )
}
