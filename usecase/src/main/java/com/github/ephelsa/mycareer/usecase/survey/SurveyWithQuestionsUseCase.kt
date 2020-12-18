package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.data.survey.SurveyRepository
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal
import com.github.ephelsa.mycareer.domain.survey.SurveyWithQuestionsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class SurveyWithQuestionsUseCase(
    private val surveyRepository: SurveyRepository,
    private val surveyQuestionsAndAnswersUseCase: StoreSurveyQuestionsAndAnswersUseCase
) {
    operator fun invoke(surveyCode: Int): Flow<ResourceRemote<SurveyWithQuestionsRemote>> = flow {
        emit(ResourceRemote.Loading())

        val response = surveyRepository.surveyWithQuestions(surveyCode)
        if (response is ResourceRemote.Success) {
            val questions = response.data
                .questionsWithAnswers
                .map { QuestionLocal(it.id, it.question, it.type.localTransform()) }
            val questionsAnswers = mutableListOf<QuestionAnswerLocal>()
            response.data
                .questionsWithAnswers
                .filter { !it.answers.isNullOrEmpty() }
                .forEach { question ->
                    val answers = question.answers!!
                        .map { QuestionAnswerLocal(question.id, it.id, it.value) }
                    questionsAnswers.addAll(answers)
                }

            surveyQuestionsAndAnswersUseCase(questions, questionsAnswers)
                .catch { emit(response) }
                .collect {
                    if (it is ResourceLocal.Success) {
                        emit(response)
                    }
                }
        } else if (response is ResourceRemote.Error) {
            emit(response)
        }
    }
}
