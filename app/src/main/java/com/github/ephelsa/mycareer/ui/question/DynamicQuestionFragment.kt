package com.github.ephelsa.mycareer.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ephelsa.mycareer.databinding.FragmentDynamicQuestionBinding
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class DynamicQuestionFragment private constructor(
    private val question: QuestionAndQuestionsAnswersLocal,
    private val answeredListener: AnsweredListener
) : BaseFragment<FragmentDynamicQuestionBinding>() {

    companion object {

        @Synchronized
        fun newInstance(
            question: QuestionAndQuestionsAnswersLocal,
            answeredListener: AnsweredListener
        ) = DynamicQuestionFragment(question, answeredListener)
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
        binding.questionText.text = question.question
        val answers = DynamicQuestionView.Builder()
            .questionID(question.questionId.toString())
            .answers(question.answers)
            .questionType(question.type)
            .build(requireContext())

        binding.answerContainer.removeAllViews()
        binding.answerContainer.addView(answers.view())

        answeredListener.onAnswered(question)
    }

    fun interface AnsweredListener {
        fun onAnswered(question: QuestionAndQuestionsAnswersLocal)
    }
}
