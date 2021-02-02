package com.github.ephelsa.mycareer.domain.shared

sealed class ResourceRemote<T : Any?>(
    private val remoteStatus: StatusRemote? = null,
    private val remoteData: T? = null,
    private val remoteError: ErrorRemote? = null,
) {
    class Loading<D : Any> : ResourceRemote<D>()
    class Success<D : Any>(val status: StatusRemote, val data: D) : ResourceRemote<D>(status, data)
    class Error<D : Any>(val status: StatusRemote, val error: ErrorRemote) :
        ResourceRemote<D>(status, remoteError = error)

    class Complete<D : Any> : ResourceRemote<D>()
}
