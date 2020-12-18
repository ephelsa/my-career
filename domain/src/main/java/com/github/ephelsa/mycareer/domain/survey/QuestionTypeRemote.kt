package com.github.ephelsa.mycareer.domain.survey

import com.github.ephelsa.mycareer.domain.shared.RemoteMapper

enum class QuestionTypeRemote : RemoteMapper<QuestionTypeLocal> {
    SELECT {
        override fun localTransform(): QuestionTypeLocal = QuestionTypeLocal.SELECT
    },
    TEXT {
        override fun localTransform(): QuestionTypeLocal = QuestionTypeLocal.TEXT
    },
}
