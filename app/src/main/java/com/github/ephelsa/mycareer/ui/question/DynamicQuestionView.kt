package com.github.ephelsa.mycareer.ui.question

import android.content.Context
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionTypeLocal

class DynamicQuestionView private constructor(
    private val context: Context,
    private val questionID: String?,
    private val questionType: QuestionTypeLocal?,
    private val answers: List<QuestionAnswerLocal?>?,
    private val checkedChangeListener: CompoundButton.OnCheckedChangeListener?,
    private val textChangedListener: TextWatcher?
) {

    class ViewExtractor(
        private val questionType: QuestionTypeLocal,
        private val view: View?
    ) {
        fun answer(): String {
            return when (questionType) {
                QuestionTypeLocal.SELECT -> viewSelect()
                QuestionTypeLocal.TEXT -> viewText()
            }
        }

        private fun viewSelect(): String {
            return (view as RadioButton?)?.tag.toString()
        }

        private fun viewText(): String {
            return (view as EditText?)?.text.toString()
        }
    }

    data class Builder(
        private val questionID: String? = null,
        private val questionType: QuestionTypeLocal? = null,
        private val answers: List<QuestionAnswerLocal?>? = null,
        private val checkedChangeListener: CompoundButton.OnCheckedChangeListener? = null,
        private val textChangedListener: TextWatcher? = null
    ) {
        fun build(context: Context) = DynamicQuestionView(
            context,
            questionID,
            questionType,
            answers,
            checkedChangeListener,
            textChangedListener
        )
    }

    fun view(): View {
        return viewFactory()
    }

    private fun viewFactory(): View {
        return when (questionType) {
            QuestionTypeLocal.SELECT -> viewTypeSelect()
            QuestionTypeLocal.TEXT -> viewTypeText()
            null -> throw NullPointerException("questionType must not be null")
        }
    }

    private fun viewTypeSelect(): View {
        val radioGroup: RadioGroup = RadioGroup(context).apply {
            tag = questionID
        }

        answers?.forEach {
            val option: RadioButton = RadioButton(context).apply {
                tag = it?.id ?: ""
                text = it?.answer ?: ""
                setOnCheckedChangeListener(checkedChangeListener)
            }

            radioGroup.addView(option)
        }

        return radioGroup
    }

    private fun viewTypeText(): View {
        return EditText(context).apply {
            tag = questionID
            addTextChangedListener(textChangedListener)
        }
    }
}
