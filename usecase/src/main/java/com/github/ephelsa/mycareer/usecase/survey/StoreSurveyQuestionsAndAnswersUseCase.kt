package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionAnswerLocal
import com.github.ephelsa.mycareer.domain.survey.QuestionLocal
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class StoreSurveyQuestionsAndAnswersUseCase(
    private val storeSurveyQuestionsUseCase: StoreSurveyQuestionsUseCase,
    private val storeSurveyQuestionAnswersUseCase: StoreSurveyQuestionAnswersUseCase,
    private val deleteStoredQuestionsAndAnswersUseCase: DeleteStoredQuestionsAndAnswersUseCase
) {
    operator fun invoke(
        questionsLocal: List<QuestionLocal>,
        questionsAnswersLocal: List<QuestionAnswerLocal>
    ) = flow {
        val errorEmit: suspend () -> Unit = {
            emit(ResourceLocal.Error(UnsupportedOperationException()))
        }

        val storedOperation: suspend () -> Unit = {
            storeSurveyQuestionsUseCase(questionsLocal)
                .catch { errorEmit() }
                .collect {
                    if (it is ResourceLocal.Success) {
                        storeSurveyQuestionAnswersUseCase(questionsAnswersLocal)
                            .catch { errorEmit() }
                            .collect { res -> emit(res) }
                    } else if (it is ResourceLocal.Error) {
                        errorEmit()
                    }
                }
        }

        deleteStoredQuestionsAndAnswersUseCase()
            .catch { errorEmit() }
            .collect {
                if (it is ResourceLocal.Success) {
                    storedOperation()
                } else if (it is ResourceLocal.Error) {
                    errorEmit()
                }
            }
    }
}
