package com.github.ephelsa.mycareer.ui.question

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.survey.StoredQuestionsAndQuestionAnswersUseCase
import kotlinx.coroutines.CoroutineDispatcher

class QuestionViewModel @ViewModelInject constructor(
    storedQuestionsAndQuestionAnswersUseCase: StoredQuestionsAndQuestionAnswersUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    val storedQuestionsAndQuestionAnswers = storedQuestionsAndQuestionAnswersUseCase().asLiveData()
}
