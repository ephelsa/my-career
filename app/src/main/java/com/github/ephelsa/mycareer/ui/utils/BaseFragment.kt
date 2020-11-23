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
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {
    private var _binding: Binding? = null
    protected val binding: Binding get() = _binding!!

    abstract fun initializeBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    protected fun navigate(direction: NavDirections) {
        findNavController().navigate(direction)
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
                    if (onLoading != null) onLoading(it)
                }
                is ResourceRemote.Success -> {
                    if (onSuccess != null) onSuccess(it)
                    if (onComplete != null) onComplete()
                }
                is ResourceRemote.Error -> {
                    if (onError != null) onError(it)
                    if (onComplete != null) onComplete()
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
