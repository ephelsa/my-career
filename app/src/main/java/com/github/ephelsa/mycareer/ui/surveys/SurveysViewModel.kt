package com.github.ephelsa.mycareer.ui.surveys

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.survey.SurveysUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SurveysViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    surveysUseCase: SurveysUseCase
) : ScopedViewModel(uiDispatcher) {

    val surveys = surveysUseCase().asLiveData()
}
