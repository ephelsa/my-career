package com.github.ephelsa.mycareer.ui.question

import androidx.hilt.lifecycle.ViewModelInject
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import kotlinx.coroutines.CoroutineDispatcher

class QuestionViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher)
