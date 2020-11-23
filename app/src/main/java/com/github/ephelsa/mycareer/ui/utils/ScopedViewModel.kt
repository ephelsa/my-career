package com.github.ephelsa.mycareer.ui.utils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

private interface ScopedCoroutine : CoroutineScope {
    var job: Job
    val uiDispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = uiDispatcher + job

    fun createScope() {
        job = SupervisorJob()
    }

    fun destroyScope() {
        job.cancel()
    }
}

private class ScopedCoroutineImpl(
    override val uiDispatcher: CoroutineDispatcher
) : ScopedCoroutine {
    override lateinit var job: Job
}

open class ScopedViewModel(
    uiDispatcher: CoroutineDispatcher
) : ViewModel(), ScopedCoroutine by ScopedCoroutineImpl(uiDispatcher) {

    init {
        createScope()
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}
