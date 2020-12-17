package com.github.ephelsa.mycareer.domain.shared

fun interface RemoteMapper<RemoteType : Any> {
    fun localTransform(): RemoteType
}

fun interface LocalMapper<LocalType : Any> {
    fun remoteTransform(): LocalType
}
