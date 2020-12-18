package com.github.ephelsa.mycareer.delivery.survey.pojo

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.survey.QuestionTypeRemote
import com.google.gson.annotations.SerializedName

enum class QuestionTypeJSON : DomainMappable<QuestionTypeRemote> {
    @SerializedName("select")
    SELECT {
        override fun toDomain(): QuestionTypeRemote = QuestionTypeRemote.SELECT
    },

    @SerializedName("text")
    TEXT {
        override fun toDomain(): QuestionTypeRemote = QuestionTypeRemote.TEXT
    },
}
