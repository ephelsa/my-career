package com.github.ephelsa.mycareer.delivery.shared.local

import com.github.ephelsa.mycareer.domain.shared.ResourceLocal

object LocalHandler {

    fun <T : Any> handleSuccess(data: T): ResourceLocal.Success<T> {
        return ResourceLocal.Success(data)
    }

    fun <T : Any> handleError(exception: Exception): ResourceLocal.Error<T> {
        // NullPointException = resource not found
        return ResourceLocal.Error(exception)
    }
}
