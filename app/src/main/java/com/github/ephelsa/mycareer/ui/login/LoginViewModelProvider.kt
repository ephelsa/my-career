package com.github.ephelsa.mycareer.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.ephelsa.mycareer.data.auth.AuthRepository
import com.github.ephelsa.mycareer.infraestructure.auth.remote.AuthRemoteRepository
import com.github.ephelsa.mycareer.infraestructure.shared.remote.RetrofitBuild
import com.github.ephelsa.mycareer.usecase.auth.LoginUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.create

class LoginViewModelProvider : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val authRemoteRepository = AuthRemoteRepository(RetrofitBuild.retrofit.create())
        val authRepository = AuthRepository(authRemoteRepository)

        return modelClass.getConstructor(
            CoroutineDispatcher::class.java,
            LoginUseCase::class.java
        ).newInstance(
            Dispatchers.IO,
            LoginUseCase(authRepository)
        )
    }
}
