package com.github.ephelsa.mycareer.ui.question

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import com.github.ephelsa.mycareer.databinding.FragmentDynamicQuestionBinding
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class DynamicQuestionFragment private constructor(
    private val question: QuestionAndQuestionsAnswersLocal,
    private val answeredListener: AnsweredListener
) : BaseFragment<FragmentDynamicQuestionBinding>(),
    CompoundButton.OnCheckedChangeListener,
    TextWatcher {

    companion object {
        private val TAG = DynamicQuestionFragment::class.java.simpleName

        @Synchronized
        fun newInstance(
            question: QuestionAndQuestionsAnswersLocal,
            answeredListener: AnsweredListener
        ) = DynamicQuestionFragment(question, answeredListener)
    }

    fun interface AnsweredListener {
        fun onAnswered(answer: String, isValid: Boolean, question: QuestionAndQuestionsAnswersLocal)
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDynamicQuestionBinding =
        FragmentDynamicQuestionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareToDisplay()
    }

    private fun prepareToDisplay() {
        val answers = DynamicQuestionView.Builder(
            questionID = question.questionId.toString(),
            answers = question.answers,
            questionType = question.type,
            checkedChangeListener = this,
            textChangedListener = this
        ).build(requireContext())

        // Show the question
        binding.questionText.text = question.question

        // Clean and add answers options/fields
        binding.answerContainer.removeAllViews()
        binding.answerContainer.addView(answers.view())
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val answer = DynamicQuestionView.ViewExtractor(question.type, buttonView)
        answeredListener.onAnswered(answer.answer(), isValid = true, question = question)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Not necessary
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        answeredListener.onAnswered(s.toString(), isValid = !s.isNullOrEmpty(), question = question)
    }

    override fun afterTextChanged(s: Editable?) {
        // Not necessary
    }
}
