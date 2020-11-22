package com.github.ephelsa.mycareer.ui.registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.ephelsa.mycareer.databinding.FragmentRegistryBinding
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class RegistryFragment : BaseFragment<FragmentRegistryBinding>(), View.OnClickListener {

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistryBinding {
        return FragmentRegistryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListener()
    }

    private fun bindClickListener() {
        with(binding) {
            loginButton.setOnClickListener(this@RegistryFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginButton -> findNavController().popBackStack()
        }
    }
}
