package com.github.ephelsa.mycareer.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentResultBinding
import com.github.ephelsa.mycareer.ui.utils.BaseFragment

class ResultFragment : BaseFragment<FragmentResultBinding>(), View.OnClickListener {

    private val args: ResultFragmentArgs by navArgs()
    private val directions = ResultFragmentDirections

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindToolbar()
        binding.bindClickListener()
    }

    private fun FragmentResultBinding.bindToolbar() {
        toolbar.actionButton.text = getString(R.string.button_home)
    }

    private fun FragmentResultBinding.bindClickListener() {
        toolbar.actionButton.setOnClickListener(this@ResultFragment)
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.toolbar.actionButton -> performActionToolbarClick()
        }
    }

    private fun performActionToolbarClick() {
        navigate(directions.resultFragmentToSurveysFragment())
    }
}
