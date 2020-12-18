package com.github.ephelsa.mycareer.domain.survey

import com.github.ephelsa.mycareer.domain.shared.LocalMapper

enum class QuestionTypeLocal : LocalMapper<QuestionTypeRemote> {
    SELECT {
        override fun remoteTransform(): QuestionTypeRemote = QuestionTypeRemote.SELECT
    },
    TEXT {
        override fun remoteTransform(): QuestionTypeRemote = QuestionTypeRemote.TEXT
    },
}