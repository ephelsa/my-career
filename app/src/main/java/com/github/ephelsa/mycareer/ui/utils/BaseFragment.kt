package com.github.ephelsa.mycareer.ui.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.ui.dialog.ErrorDialog
import com.github.ephelsa.mycareer.ui.dialog.LoaderDialog

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    private val loaderDialog = LoaderDialog()
    private val errorDialog = ErrorDialog()

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
    }

    abstract fun initializeBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun displayLoader() {
        if (!loaderDialog.isAdded) loaderDialog.show(parentFragmentManager, TAG)
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun hideLoader() {
        loaderDialog.dismiss()
    }

    protected fun displayError(errorRemote: ErrorRemote) {
        errorDialog.apply {
            errorTitle = errorRemote.message
            errorMessage = errorRemote.details
            show(this@BaseFragment.parentFragmentManager, TAG)
        }
    }

    protected fun <T : Any> LiveData<ResourceRemote<T>>.handleObservable(
        onLoading: ((ResourceRemote.Loading<T>) -> Unit)? = null,
        onSuccess: ((ResourceRemote.Success<T>) -> Unit)? = null,
        onError: ((ResourceRemote.Error<T>) -> Unit)? = null,
        onComplete: (() -> Unit)? = null
    ) {
        this.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceRemote.Loading -> {
                    displayLoader()
                    if (onLoading != null) onLoading(it)
                }
                is ResourceRemote.Success -> {
                    if (onSuccess != null) onSuccess(it)
                    if (onComplete != null) onComplete()
                    hideLoader()
                }
                is ResourceRemote.Error -> {
                    if (onError != null) onError(it)
                    if (onComplete != null) onComplete()
                    displayError(it.error)
                    hideLoader()
                }
                is ResourceRemote.Complete -> if (onComplete != null) onComplete()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = initializeBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
