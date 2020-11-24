package com.github.ephelsa.mycareer.ui.login

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.auth.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher

class LoginViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase
) : ScopedViewModel(uiDispatcher) {

    private val _ui = MutableLiveData<UI>()
    val ui: LiveData<UI> get() = _ui

    fun login(authCredentialRemote: AuthCredentialRemote) =
        loginUseCase(authCredentialRemote).asLiveData()

    fun enableLoginButton(email: String, password: String, minPasswordSize: Int) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= minPasswordSize

        _ui.value = UI.DisplayEmailError(!isEmailValid)
        _ui.value = UI.DisplayPasswordError(!isPasswordValid)
        _ui.value = UI.IsLoginEnabled(isEmailValid && isPasswordValid)
    }

    sealed class UI {
        data class IsLoginEnabled(val isEnabled: Boolean) : UI()
        data class DisplayPasswordError(val display: Boolean) : UI()
        data class DisplayEmailError(val display: Boolean) : UI()
    }
}
