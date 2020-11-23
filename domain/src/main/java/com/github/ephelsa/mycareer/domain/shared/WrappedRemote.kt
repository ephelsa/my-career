package com.github.ephelsa.mycareer.domain.shared

data class WrappedRemote<T : Any>(
    val status: StatusRemote,
    val result: T?,
    val error: ErrorRemote?
)
