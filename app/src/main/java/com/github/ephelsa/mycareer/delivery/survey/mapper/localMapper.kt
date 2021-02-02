package com.github.ephelsa.mycareer.delivery.survey.mapper

import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAndQuestionsAnswersRelation
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAnswerEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.UserAnswerEntity
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal

fun QuestionLocal.toDelivery() = QuestionEntity(id, question, type)

fun List<QuestionLocal>.toDelivery(): List<QuestionEntity> = map { it.toDelivery() }

fun QuestionAnswerLocal.toDelivery() = QuestionAnswerEntity(questionId, id, answer)

@JvmName("toDeliveryQuestionAnswerLocal")
fun List<QuestionAnswerLocal>.toDelivery(): List<QuestionAnswerEntity> = map { it.toDelivery() }

fun List<QuestionAnswerEntity>.toDomain() = map { it.toDomain() }

@JvmName("toDomainQuestionAndQuestionsAnswersRelation")
fun List<QuestionAndQuestionsAnswersRelation>.toDomain(): List<QuestionAndQuestionsAnswersLocal> =
    map { it.toDomain() }

fun UserAnswerLocal.toDelivery() = UserAnswerEntity(surveyID, questionID, answer, resolveAttempt)

@JvmName("toDomainUserAnswerEntity")
fun List<UserAnswerEntity>.toDomain(): List<UserAnswerLocal> = map { it.toDomain() }
