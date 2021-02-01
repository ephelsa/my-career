package com.github.ephelsa.mycareer.ui.login

import android.util.Patterns
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.ui.utils.ScopedViewModel
import com.github.ephelsa.mycareer.usecase.auth.GetStoredCredentialsUseCase
import com.github.ephelsa.mycareer.usecase.auth.LoginUseCase
import com.github.ephelsa.mycareer.usecase.user.DownloadAndStoreUserInformationByEmailUseCase
import kotlinx.coroutines.CoroutineDispatcher

class LoginViewModel @ViewModelInject constructor(
    uiDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase,
    private val downloadAndStoreUserInformationByEmailUseCase: DownloadAndStoreUserInformationByEmailUseCase,
    getStoredCredentialsUseCase: GetStoredCredentialsUseCase
) : ScopedViewModel(uiDispatcher) {

    private val _ui = MutableLiveData<UI>()
    val ui: LiveData<UI> get() = _ui

    val storedCredentials = getStoredCredentialsUseCase().asLiveData()

    fun login(authCredentialRemote: AuthCredentialRemote) =
        loginUseCase(authCredentialRemote).asLiveData()

    fun downloadAndStoreUserInfoByEmail(email: String) =
        downloadAndStoreUserInformationByEmailUseCase(email).asLiveData()

    fun enableLoginButton(email: String, password: String, minPasswordSize: Int) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= minPasswordSize

        _ui.value = UI.InvalidEmail(!isEmailValid)
        _ui.value = UI.InvalidPassword(!isPasswordValid)
        _ui.value = UI.IsLoginEnabled(isEmailValid && isPasswordValid)
    }

    sealed class UI {
        data class IsLoginEnabled(val isEnabled: Boolean) : UI()
        data class InvalidPassword(val display: Boolean) : UI()
        data class InvalidEmail(val display: Boolean) : UI()
    }
}
