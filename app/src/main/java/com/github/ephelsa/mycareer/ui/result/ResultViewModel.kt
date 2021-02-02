package com.github.ephelsa.mycareer.ui.result

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.survey.ClassificationRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.survey.ClassifySurveyUseCase
import kotlinx.coroutines.CoroutineDispatcher

class ResultViewModel @ViewModelInject constructor(
    private val classifySurveyUseCase: ClassifySurveyUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    companion object {
        private const val RANK = 10
    }

    fun classifySurvey(surveyId: String, resolveAttempt: Int) =
        classifySurveyUseCase(surveyId, resolveAttempt).asLiveData()

    fun ranking(classificationRemoteList: List<ClassificationRemote>): List<ClassificationRemote> {
        return classificationRemoteList
            .sortedByDescending { it.percent }
            .take(RANK)
    }
}
