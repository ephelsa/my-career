package com.github.ephelsa.mycareer.delivery.shared.local

import androidx.room.TypeConverter
import com.github.ephelsa.mycareer.domain.survey.QuestionTypeLocal

class Converters {
    @TypeConverter
    fun fromQuestionTypeLocal(type: QuestionTypeLocal?): String? = type?.name

    @TypeConverter
    fun toQuestionTypeLocal(type: String?): QuestionTypeLocal? {
        return QuestionTypeLocal.values().find { it.name == type }
    }
}
