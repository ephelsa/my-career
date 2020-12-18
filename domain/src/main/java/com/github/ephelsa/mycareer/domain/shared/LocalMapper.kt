package com.github.ephelsa.mycareer.domain.shared

fun interface LocalMapper<RemoteType : Any> {
    fun remoteTransform(): RemoteType
}
