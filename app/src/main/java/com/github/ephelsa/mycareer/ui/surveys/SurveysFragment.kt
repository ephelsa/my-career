package com.github.ephelsa.mycareer.ui.surveys

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.ephelsa.mycareer.databinding.FragmentSurveysBinding
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class SurveysFragment : BaseFragment<FragmentSurveysBinding>() {

    private val viewModel: SurveysViewModel by viewModels()

    companion object {
        private val TAG = SurveysFragment::class.java.simpleName
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSurveysBinding {
        return FragmentSurveysBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.surveys.handleObservable(
            onSuccess = { Log.v(TAG, "${it.data}") },
            onError = { Log.v(TAG, "${it.error}") }
        )
    }
}
