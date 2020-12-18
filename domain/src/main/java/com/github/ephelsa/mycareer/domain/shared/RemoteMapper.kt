package com.github.ephelsa.mycareer.domain.shared

interface RemoteMapper<LocalType : Any> {
    fun localTransform(): LocalType
}
