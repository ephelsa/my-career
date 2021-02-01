package com.github.ephelsa.mycareer.ui.question

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.UserAnswerLocal
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.survey.StoreUserAnswerUseCase
import com.github.ephelsa.mycareer.usecase.survey.StoredQuestionsAndQuestionAnswersUseCase
import kotlinx.coroutines.CoroutineDispatcher

class QuestionViewModel @ViewModelInject constructor(
    storedQuestionsAndQuestionAnswersUseCase: StoredQuestionsAndQuestionAnswersUseCase,
    private val storeUserAnswerUseCase: StoreUserAnswerUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    val storedQuestionsAndQuestionAnswers = storedQuestionsAndQuestionAnswersUseCase().asLiveData()

    fun storeUserAnswer(
        userAnswerLocal: UserAnswerLocal,
        isValid: Boolean
    ): LiveData<ResourceLocal<Unit>>? {
        return storeUserAnswerUseCase(userAnswerLocal).asLiveData().takeIf { isValid }
    }
}
