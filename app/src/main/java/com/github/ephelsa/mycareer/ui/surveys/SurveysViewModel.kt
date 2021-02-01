package com.github.ephelsa.mycareer.ui.surveys

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.domain.user.UserRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.auth.DeleteStoredSessionsUseCase
import com.github.ephelsa.mycareer.usecase.survey.SurveyWithQuestionsUseCase
import com.github.ephelsa.mycareer.usecase.survey.SurveysUseCase
import com.github.ephelsa.mycareer.usecase.user.RetrieveUserInformationUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SurveysViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    private val surveysUseCase: SurveysUseCase,
    private val surveyWithQuestionsUseCase: SurveyWithQuestionsUseCase,
    deleteStoredSessionsUseCase: DeleteStoredSessionsUseCase,
    retrieveUserInformationUseCase: RetrieveUserInformationUseCase
) : ScopedViewModel(uiDispatcher) {

    private val _showSurveyRecycler = MutableLiveData<Boolean>()
    val showSurveyRecycler: LiveData<Boolean> get() = _showSurveyRecycler

    fun surveys(userRemote: UserRemote) = surveysUseCase(userRemote).asLiveData()

    val retrieveUserInformation = retrieveUserInformationUseCase().asLiveData()

    val deleteStoredSessions = deleteStoredSessionsUseCase().asLiveData()

    fun surveyWithQuestions(surveyCode: Int) = surveyWithQuestionsUseCase(surveyCode).asLiveData()

    fun verifyListOfSurveys(surveys: List<SurveyRemote>) {
        _showSurveyRecycler.value = surveys.isNotEmpty()
    }
}
