package com.github.ephelsa.mycareer.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.ephelsa.mycareer.databinding.FragmentLoginBinding
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(), View.OnClickListener {

    private val directions = LoginFragmentDirections

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

    override fun onClick(v: View?) {
        when (v) {
            binding.registryButton -> navigate(directions.registryFragment())
        }
    }
}
