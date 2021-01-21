package com.github.ephelsa.mycareer.ui.question

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionTypeLocal

class DynamicQuestionView private constructor(
    private val context: Context,
    private val questionID: String?,
    private val questionType: QuestionTypeLocal?,
    private val answers: List<QuestionAnswerLocal?>?
) {

    data class Builder(
        private var questionID: String? = null,
        private var questionType: QuestionTypeLocal? = null,
        private var answers: List<QuestionAnswerLocal?>? = null
    ) {
        fun questionID(questionID: String): Builder {
            this.questionID = questionID

            return this
        }

        fun questionType(questionType: QuestionTypeLocal): Builder {
            this.questionType = questionType

            return this
        }

        fun answers(answers: List<QuestionAnswerLocal?>?): Builder {
            this.answers = answers

            return this
        }

        fun build(context: Context) =
            DynamicQuestionView(context, questionID, questionType, answers)
    }

    fun view(): View {
        return viewFactory()
    }

    private fun viewFactory(): View {
        if (questionType == null) {
            throw NullPointerException("questionType must not be null")
        }

        return when (questionType) {
            QuestionTypeLocal.SELECT -> viewTypeSelect()
            QuestionTypeLocal.TEXT -> viewTypeText()
        }
    }

    private fun viewTypeSelect(): View {
        if (answers.isNullOrEmpty()) {
            throw NullPointerException("For type SELECT answers are required")
        }

        val radioGroup: RadioGroup = RadioGroup(context).apply {
            tag = questionID
        }

        answers.forEach {
            val option: RadioButton = RadioButton(context).apply {
                tag = it?.id
                text = it?.answer ?: ""
            }

            radioGroup.addView(option)
        }

        return radioGroup
    }

    private fun viewTypeText(): View {
        return EditText(context).apply {
            tag = questionID
        }
    }
}
