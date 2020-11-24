package com.github.ephelsa.mycareer.ui.utils

import android.os.Bundle
import android.util.Log
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
import com.github.ephelsa.mycareer.ui.dialog.DialogListener
import com.github.ephelsa.mycareer.ui.dialog.ErrorDialog
import com.github.ephelsa.mycareer.ui.dialog.LoaderDialog
import com.github.ephelsa.mycareer.ui.dialog.SuccessDialog

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    private val loaderDialog = LoaderDialog()
    private val successDialog = SuccessDialog()
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
        try {
            loaderDialog.show(parentFragmentManager, TAG)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun hideLoader() {
        loaderDialog.dismiss()
    }

    protected fun displaySuccess(dialogListener: DialogListener? = null) {
        successDialog.dialogListener = dialogListener

        try {
            successDialog.show(parentFragmentManager, TAG)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun displayError(errorRemote: ErrorRemote) {
        errorDialog.apply {
            errorTitle = errorRemote.message
            errorMessage = errorRemote.details
        }

        try {
            errorDialog.show(parentFragmentManager, TAG)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    protected fun <T : Any> LiveData<ResourceRemote<T>>.handleObservable(
        onLoading: ((ResourceRemote.Loading<T>) -> Unit)? = null,
        onSuccess: ((ResourceRemote.Success<T>) -> Unit)? = null,
        onError: ((ResourceRemote.Error<T>) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,

        // Optionals
        enableDefaultLoader: Boolean = true,
        enableDefaultError: Boolean = true
    ) {
        this.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceRemote.Loading -> {
                    if (enableDefaultLoader) displayLoader()
                    if (onLoading != null) onLoading(it)
                }
                is ResourceRemote.Success -> {
                    if (enableDefaultLoader) hideLoader()
                    if (onSuccess != null) onSuccess(it)
                    if (onComplete != null) onComplete()
                }
                is ResourceRemote.Error -> {
                    if (enableDefaultLoader) hideLoader()
                    if (onError != null) onError(it)
                    if (onComplete != null) onComplete()
                    if (enableDefaultError) displayError(it.error)
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
