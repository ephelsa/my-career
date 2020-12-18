package com.github.ephelsa.mycareer.usecase.survey

import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

class DeleteStoredQuestionsAndAnswersUseCase(
    private val deleteStoredQuestionsUseCase: DeleteStoredQuestionsUseCase,
    private val deleteStoredQuestionsAnswersUseCase: DeleteStoredQuestionsAnswersUseCase
) {
    operator fun invoke() = flow {
        val emitError: suspend () -> Unit = {
            emit(ResourceLocal.Error(UnsupportedOperationException()))
        }

        deleteStoredQuestionsAnswersUseCase()
            .catch { emitError() }
            .collect {
                if (it is ResourceLocal.Success) {
                    deleteStoredQuestionsUseCase()
                        .catch { emitError() }
                        .collect { res -> emit(res) }
                } else if (it is ResourceLocal.Error) {
                    emitError()
                }
            }
    }
}
