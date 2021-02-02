package com.github.ephelsa.mycareer.delivery.survey.mapper

import com.github.ephelsa.mycareer.delivery.survey.pojo.UserAnswerJSON
import com.github.ephelsa.mycareer.domain.survey.UserAnswerRemote

fun UserAnswerRemote.toDelivery() = UserAnswerJSON(
    email,
    documentType,
    document,
    surveyId,
    questionId,
    answer,
    resolveAttempt
)

fun List<UserAnswerRemote>.toDelivery() = map { it.toDelivery() }
