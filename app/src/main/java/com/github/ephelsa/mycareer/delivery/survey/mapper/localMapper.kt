package com.github.ephelsa.mycareer.delivery.survey.mapper

import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAndQuestionsAnswersRelation
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAnswerEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionEntity
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal

fun QuestionLocal.toDelivery() = QuestionEntity(id, question, type)

fun List<QuestionLocal>.toDelivery(): List<QuestionEntity> = map { it.toDelivery() }

fun QuestionAnswerLocal.toDelivery() = QuestionAnswerEntity(questionId, id, answer)

@JvmName("toDeliveryQuestionAnswerLocal")
fun List<QuestionAnswerLocal>.toDelivery(): List<QuestionAnswerEntity> = map { it.toDelivery() }

fun List<QuestionAnswerEntity>.toDomain() = map { it.toDomain() }

@JvmName("toDomainQuestionAndQuestionsAnswersRelation")
fun List<QuestionAndQuestionsAnswersRelation>.toDomain(): List<QuestionAndQuestionsAnswersLocal> =
    map { it.toDomain() }
