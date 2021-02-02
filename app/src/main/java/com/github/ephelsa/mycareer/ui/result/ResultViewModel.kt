package com.github.ephelsa.mycareer.ui.result

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.survey.ClassifySurveyUseCase
import kotlinx.coroutines.CoroutineDispatcher

class ResultViewModel @ViewModelInject constructor(
    private val classifySurveyUseCase: ClassifySurveyUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    fun classifySurvey(surveyId: String, resolveAttempt: Int) =
        classifySurveyUseCase(surveyId, resolveAttempt).asLiveData()
}
