package com.github.ephelsa.mycareer.ui.registry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentRegistryBinding
import com.github.ephelsa.mycareer.domain.location.CountryRemote
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class RegistryFragment : BaseFragment<FragmentRegistryBinding>(), View.OnClickListener {

    companion object {
        private val TAG = RegistryFragment::class.java.simpleName
    }

    private val viewModel: RegistryViewModel by lazy {
        ViewModelProvider(this, RegistryViewModelProvider()).get(RegistryViewModel::class.java)
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

    private val tempToast: (String) -> Unit = {
        Log.v(TAG, it)
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private fun handleCountries() {
        val name = CountryRemote::class.java.simpleName
        viewModel.countries.handleObservable(
            onLoading = { tempToast(getString(R.string.temp_loading, name)) },
            onSuccess = { tempToast(getString(R.string.temp_success, "${it.data}")) },
            onError = { tempToast(getString(R.string.temp_error, "${it.error}")) },
            onComplete = { tempToast(getString(R.string.temp_complete, name)) }
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
