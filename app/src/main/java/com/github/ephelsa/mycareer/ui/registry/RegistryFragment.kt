package com.github.ephelsa.mycareer.ui.registry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentRegistryBinding
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.tempToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistryFragment : BaseFragment<FragmentRegistryBinding>(), View.OnClickListener {

    private val viewModel: RegistryViewModel by viewModels()

    companion object {
        private val TAG = RegistryFragment::class.java.simpleName
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRegistryBinding {
        return FragmentRegistryBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindClickListener()

        handleCountries()
    }

    private fun bindClickListener() {
        with(binding) {
            loginButton.setOnClickListener(this@RegistryFragment)
            backButton.setOnClickListener(this@RegistryFragment)
            completeRegistryButton.setOnClickListener(this@RegistryFragment)
        }
    }

    private fun handleCountries() {
        val name = CountryRemote::class.java.simpleName
        viewModel.countries.handleObservable(
            onLoading = { requireContext().tempToast(getString(R.string.temp_loading, name), TAG) },
            onSuccess = {
                requireContext().tempToast(
                    getString(R.string.temp_success, "${it.data}"),
                    TAG
                )
            },
            onError = {
                requireContext().tempToast(
                    getString(R.string.temp_error, "${it.error}"),
                    TAG
                )
            }
        )
    }

    private fun performRegistry() {
        Toast.makeText(requireContext(), getString(R.string.app_name), Toast.LENGTH_LONG).show()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.loginButton -> findNavController().popBackStack()
            binding.backButton -> findNavController().popBackStack()
            binding.completeRegistryButton -> performRegistry()
        }
    }
}
