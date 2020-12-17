package com.github.ephelsa.mycareer.domain.shared

sealed class ResourceLocal<T : Any>(
    private val localData: T? = null,
    private val localException: Exception? = null
) {
    class Loading<D : Any> : ResourceLocal<D>()
    class Success<D : Any>(val data: D) : ResourceLocal<D>(localData = data)
    class Error<D : Any>(val error: Exception) : ResourceLocal<D>(localException = error)
    class Complete<D : Any> : ResourceLocal<D>()
}
