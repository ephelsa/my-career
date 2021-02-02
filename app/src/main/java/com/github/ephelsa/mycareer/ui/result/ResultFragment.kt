package com.github.ephelsa.mycareer.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentResultBinding
import com.github.ephelsa.mycareer.domain.survey.ClassificationRemote
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(), View.OnClickListener {

    private val viewModel: ResultViewModel by viewModels()
    private val args: ResultFragmentArgs by navArgs()
    private val directions = ResultFragmentDirections

    companion object {
        private val TAG = ResultFragment::class.java.simpleName
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindToolbar()
        binding.bindClickListener()
        loadResults()
    }

    private fun FragmentResultBinding.bindToolbar() {
        toolbar.actionButton.text = getString(R.string.button_home)
    }

    private fun FragmentResultBinding.bindClickListener() {
        toolbar.actionButton.setOnClickListener(this@ResultFragment)
    }

    private fun loadResults() {
        viewModel.classifySurvey(args.surveyID, args.resolveAttempt)
            .handleObservable(onSuccess = { binding.drawGraphics(it.data) })
    }

    private fun FragmentResultBinding.drawGraphics(classificationRemoteList: List<ClassificationRemote>) {
        val top = viewModel.ranking(classificationRemoteList)
        firstProgress.apply {
            progress = top[0].percent
            labelText = "${top[0].career} - ${top[0].percent}%"
        }
        secondProgress.apply {
            progress = top[1].percent
            labelText = "${top[1].career} - ${top[1].percent}%"
        }
        thirdProgress.apply {
            progress = top[2].percent
            labelText = "${top[2].career} - ${top[2].percent}%"
        }
        fourthProgress.apply {
            progress = top[3].percent
            labelText = "${top[3].career} - ${top[3].percent}%"
        }
        fifthProgress.apply {
            progress = top[4].percent
            labelText = "${top[4].career} - ${top[4].percent}%"
        }
        sixthProgress.apply {
            progress = top[5].percent
            labelText = "${top[5].career} - ${top[5].percent}%"
        }
        seventhProgress.apply {
            progress = top[6].percent
            labelText = "${top[6].career} - ${top[6].percent}%"
        }
        eighthProgress.apply {
            progress = top[7].percent
            labelText = "${top[7].career} - ${top[7].percent}%"
        }
        ninethProgress.apply {
            progress = top[8].percent
            labelText = "${top[8].career} - ${top[8].percent}%"
        }
        tenthProgress.apply {
            progress = top[9].percent
            labelText = "${top[9].career} - ${top[9].percent}%"
        }
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
