package com.github.ephelsa.mycareer.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.auth.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher

class LoginViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase
) : ScopedViewModel(uiDispatcher) {

    fun login(authCredentialRemote: AuthCredentialRemote) =
        loginUseCase(authCredentialRemote).asLiveData()
}
