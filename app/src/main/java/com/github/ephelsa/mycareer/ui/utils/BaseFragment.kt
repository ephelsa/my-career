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
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.ui.dialog.DialogListener
import com.github.ephelsa.mycareer.ui.dialog.ErrorDialog
import com.github.ephelsa.mycareer.ui.dialog.LoaderDialog
import com.github.ephelsa.mycareer.ui.dialog.SuccessDialog
import java.util.*

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    private val loaderDialog = LoaderDialog()
    private val successDialog = SuccessDialog()
    private val errorDialog = ErrorDialog()

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
        private val LOADER_UUID = UUID.randomUUID().variant().toString()
        private val SUCCESS_UUID = UUID.randomUUID().variant().toString()
        private val ERROR_UUID = UUID.randomUUID().variant().toString()
    }

    abstract fun initializeBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    protected fun navigate(direction: NavDirections) {
        try {
            findNavController().navigate(direction)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun displayLoader() {
        try {
            if (loaderDialog.isAdded) {
                loaderDialog.dismiss()
            }
            loaderDialog.show(parentFragmentManager, LOADER_UUID)
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
            if (successDialog.isAdded) {
                successDialog.dismiss()
            }
            successDialog.show(parentFragmentManager, SUCCESS_UUID)
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun displayError(title: String, message: String) {
        errorDialog.apply {
            errorTitle = title
            errorMessage = message
        }

        try {
            if (errorDialog.isAdded) {
                errorDialog.dismiss()
            }
            errorDialog.show(parentFragmentManager, ERROR_UUID)
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
                    if (enableDefaultError) displayError(it.error.message, it.error.details)
                }
                is ResourceRemote.Complete -> if (onComplete != null) onComplete()
            }
        }
    }

    @JvmName("handleObservableLocal")
    protected fun <T : Any> LiveData<ResourceLocal<T>>.handleObservable(
        onLoading: ((ResourceLocal.Loading<T>) -> Unit)? = null,
        onSuccess: ((ResourceLocal.Success<T>) -> Unit)? = null,
        onError: ((ResourceLocal.Error<T>) -> Unit)? = null,
        onComplete: (() -> Unit)? = null,

        // Optionals
        enableDefaultLoader: Boolean = true,
        enableDefaultError: Boolean = true
    ) {
        this.observe(viewLifecycleOwner) {
            when (it) {
                is ResourceLocal.Loading -> {
                    if (enableDefaultLoader) displayLoader()
                    if (onLoading != null) onLoading(it)
                }
                is ResourceLocal.Success -> {
                    if (enableDefaultLoader) hideLoader()
                    if (onSuccess != null) onSuccess(it)
                    if (onComplete != null) onComplete()
                }
                is ResourceLocal.Error -> {
                    if (enableDefaultLoader) hideLoader()
                    if (onError != null) onError(it)
                    if (onComplete != null) onComplete()
                    if (enableDefaultError) displayError(it.error.toString(), it.error.toString())
                }
                is ResourceLocal.Complete -> if (onComplete != null) onComplete()
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
