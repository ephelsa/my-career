package com.github.ephelsa.mycareer.ui.surveys

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.survey.SurveyWithQuestionsUseCase
import com.github.ephelsa.mycareer.usecase.survey.SurveysUseCase
import com.github.ephelsa.mycareer.usecase.user.UserInformationByEmailUseCase
import kotlinx.coroutines.CoroutineDispatcher

class SurveysViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    surveysUseCase: SurveysUseCase,
    private val userInformationByEmailUseCase: UserInformationByEmailUseCase,
    private val surveyWithQuestionsUseCase: SurveyWithQuestionsUseCase,
) : ScopedViewModel(uiDispatcher) {

    private val _showSurveyRecycler = MutableLiveData<Boolean>()
    val showSurveyRecycler: LiveData<Boolean> get() = _showSurveyRecycler

    val surveys = surveysUseCase().asLiveData()

    fun userInformationByEmail(email: String) = userInformationByEmailUseCase(email).asLiveData()

    fun surveyWithQuestions(surveyCode: Int) = surveyWithQuestionsUseCase(surveyCode).asLiveData()

    fun verifyListOfSurveys(surveys: List<SurveyRemote>) {
        _showSurveyRecycler.value = surveys.isNotEmpty()
    }
}
