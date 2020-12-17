package com.github.ephelsa.mycareer.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentLoginBinding
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.ui.dialog.DialogListener
import com.github.ephelsa.mycareer.ui.login.LoginViewModel.UI
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.hasError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {

    private val directions = LoginFragmentDirections
    private val viewModel: LoginViewModel by viewModels()

    companion object {
        private val TAG = LoginFragment::class.java.simpleName
        private const val MIN_PASSWORD_SIZE = 8
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListener()
        validateFields()
        continueSession()
        viewModel.ui.observe(viewLifecycleOwner, Observer(::uiObserver))
    }

    private fun continueSession() {
        viewModel.storedCredentials.handleObservable(
            onSuccess = {
                val credentials = it.data.remoteTransform()
                fillFields(credentials)
                login(credentials)
            }
        )
    }

    private fun fillFields(credentialRemote: AuthCredentialRemote): Unit = with(binding) {
        emailInput.setText(credentialRemote.email)
        passwordInput.setText(credentialRemote.password)
    }

    private fun bindClickListener() {
        with(binding) {
            registryButton.setOnClickListener(this@LoginFragment)
            loginButton.setOnClickListener(this@LoginFragment)
        }
    }

    private fun validateFields() {
        binding.emailInput.addTextChangedListener {
            viewModel.enableLoginButton(
                it.toString(),
                binding.passwordInput.text.toString(),
                MIN_PASSWORD_SIZE
            )
        }

        binding.passwordInput.addTextChangedListener {
            viewModel.enableLoginButton(
                binding.emailInput.text.toString(),
                it.toString(),
                MIN_PASSWORD_SIZE
            )
        }
    }

    private fun uiObserver(ui: UI) {
        when (ui) {
            is UI.IsLoginEnabled -> binding.loginButton.isEnabled = ui.isEnabled
            is UI.InvalidPassword -> {
                binding.passwordContainer
                    .hasError(
                        ui.display,
                        getString(R.string.error_password_length, MIN_PASSWORD_SIZE)
                    )
            }
            is UI.InvalidEmail -> {
                binding.emailContainer
                    .hasError(ui.display, getString(R.string.error_email))
            }
        }
    }

    private fun performAuth() {
        val credentials: AuthCredentialRemote = with(binding) {
            AuthCredentialRemote(emailInput.text?.toString(), passwordInput.text?.toString())
        }
        login(credentials)
    }

    private fun login(credentialRemote: AuthCredentialRemote) {
        val successListener = object : DialogListener {
            override fun onClose(dialogFragment: DialogFragment) {
                navigate(directions.surveysFragment(credentialRemote.email!!))
            }
        }

        viewModel.login(credentialRemote).handleObservable(
            onSuccess = { displaySuccess(successListener) }
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginButton -> performAuth()
            binding.registryButton -> navigate(directions.registryFragment())
        }
    }
}
