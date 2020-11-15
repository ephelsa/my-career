package com.github.ephelsa.mycareer.ui.login

import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.ephelsa.mycareer.databinding.LoginFragmentBinding
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class LoginFragment : BaseFragment<LoginFragmentBinding>() {

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): LoginFragmentBinding = LoginFragmentBinding.inflate(inflater, container, false)
}