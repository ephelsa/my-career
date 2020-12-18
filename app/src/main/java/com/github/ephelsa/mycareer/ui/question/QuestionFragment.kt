package com.github.ephelsa.mycareer.ui.question

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.github.ephelsa.mycareer.databinding.FragmentQuestionBinding
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : BaseFragment<FragmentQuestionBinding>() {

    private val viewModel: QuestionViewModel by viewModels()

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentQuestionBinding = FragmentQuestionBinding.inflate(inflater, container, false)
}
