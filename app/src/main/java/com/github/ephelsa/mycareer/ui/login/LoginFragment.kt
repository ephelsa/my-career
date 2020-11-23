package com.github.ephelsa.mycareer.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentLoginBinding
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialRemote
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.tempToast

class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {

    private val directions = LoginFragmentDirections
    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelProvider()).get(LoginViewModel::class.java)
    }

    companion object {
        private val TAG = LoginFragment::class.java.simpleName
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListener()
    }

    private fun bindClickListener() {
        with(binding) {
            registryButton.setOnClickListener(this@LoginFragment)
            loginButton.setOnClickListener(this@LoginFragment)
        }
    }

    private fun performAuth() {
        val credentials = with(binding) {
            AuthCredentialRemote(emailInput.text?.toString(), passwordInput.text?.toString())
        }
        val name = AuthCredentialRemote::class.java.simpleName

        viewModel.login(credentials).handleObservable(
            onLoading = { requireContext().tempToast(getString(R.string.temp_loading, name), TAG) },
            onSuccess = {
                requireContext().tempToast(getString(R.string.temp_success, "${it.data}"), TAG)
            },
            onError = {
                requireContext().tempToast(getString(R.string.temp_error, "${it.error}"), TAG)
            }
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginButton -> performAuth()
            binding.registryButton -> navigate(directions.registryFragment())
        }
    }
}
