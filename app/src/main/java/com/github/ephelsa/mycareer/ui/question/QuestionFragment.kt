package com.github.ephelsa.mycareer.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentQuestionBinding
import com.github.ephelsa.mycareer.domain.survey.QuestionAndQuestionsAnswersLocal
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment :
    BaseFragment<FragmentQuestionBinding>(),
    View.OnClickListener,
    DynamicQuestionFragment.AnsweredListener {

    companion object {
        private val TAG = QuestionFragment::class.java.simpleName
    }

    private val viewModel: QuestionViewModel by viewModels()
    private lateinit var questionsAndQuestionsAnswers: List<QuestionAndQuestionsAnswersLocal>
    private val args: QuestionFragmentArgs by navArgs()
    private var currentQuestionPos: Int = 0

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuestionBinding = FragmentQuestionBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureToolbar()
        bindClickListener()
        retrieveQuestionsAndQuestionsAnswers()
    }

    private fun bindClickListener() = with(binding) {
        nextButton.setOnClickListener(this@QuestionFragment)
        beforeButton.setOnClickListener(this@QuestionFragment)
        resultsButton.setOnClickListener(this@QuestionFragment)
        errorLayout.closeButton.setOnClickListener(this@QuestionFragment)
    }

    private fun retrieveQuestionsAndQuestionsAnswers() {
        val onSuccess: (List<QuestionAndQuestionsAnswersLocal>) -> Unit = {
            questionsAndQuestionsAnswers = it

            try {
                openQuestion()
                handleQuestionCounter()
            } catch (e: Exception) {
                displayError(
                    title = getString(R.string.error_questions_and_questions_answers_empty),
                    message = getString(R.string.error_questions_and_questions_answers_empty)
                )
            }
        }

        viewModel.storedQuestionsAndQuestionAnswers.handleObservable(
            onSuccess = { onSuccess(it.data) }
        )
    }

    private fun openQuestion() {
        val dynamic = DynamicQuestionFragment
            .newInstance(questionsAndQuestionsAnswers[currentQuestionPos], this)

        handleButtonEnableBounds(false)
        handleQuestionCounter()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.question_fragment_container, dynamic)
            .addToBackStack(null)
            .commit()
    }

    private fun configureToolbar() {
        binding.toolbar.titleToolbarText.text = getString(R.string.label_profiling)
        binding.toolbar.logoutButton.text = getString(R.string.button_home)
        binding.toolbar.logoutButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun handleQuestionCounter() {
        val humanCurrent = currentQuestionPos + 1
        val size = questionsAndQuestionsAnswers.size
        binding.currentQuestionText.text =
            getString(R.string.label_question_counter, humanCurrent, size)
    }

    private fun handleViewResults(isComplete: Boolean) {
        binding.resultsButton.isVisible = isComplete
    }

    private fun handleButtonEnableBounds(isValid: Boolean) {
        val isEnd = currentQuestionPos + 1 == questionsAndQuestionsAnswers.size
        binding.nextButton.isEnabled = !isEnd && isValid
        binding.beforeButton.isEnabled = currentQuestionPos > 0
        handleViewResults(isEnd && isValid)
    }

    private fun storeAnswer(
        answer: String,
        isValid: Boolean,
        question: QuestionAndQuestionsAnswersLocal
    ) {
        val userAnswerLocal = UserAnswerLocal(
            surveyID = args.surveyID.toString(),
            questionID = question.questionId.toString(),
            answer = answer,
            resolveAttempt = args.resolveAttempt
        )

        viewModel.storeUserAnswer(userAnswerLocal, isValid)
            ?.handleObservable(
                enableDefaultLoader = false,
                enableDefaultError = false,
                onSuccess = { handleButtonEnableBounds(isValid) },
                onError = { handleError(it.error.toString()) }
            )
    }

    private fun handleError(message: String) {
        binding.errorContainer.isVisible = true
        binding.errorLayout.errorText.text = message
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.nextButton -> performNextQuestion()
            binding.beforeButton -> performLastQuestion()
            binding.resultsButton -> performResults()
            binding.errorLayout.closeButton -> performCloseError()
        }
    }

    private fun performNextQuestion() {
        currentQuestionPos += 1
        openQuestion()
    }

    private fun performLastQuestion() {
        currentQuestionPos -= 1
        openQuestion()
    }

    private fun performResults() {
        Toast.makeText(
            requireContext(),
            getString(R.string.warning_work_in_progress),
            Toast.LENGTH_LONG
        ).show()
    }

    private fun performCloseError() {
        binding.errorContainer.isVisible = false
    }

    override fun onAnswered(
        answer: String,
        isValid: Boolean,
        question: QuestionAndQuestionsAnswersLocal
    ) {
        storeAnswer(answer, isValid, question)
    }
}
