package com.github.ephelsa.mycareer.ui.surveys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.FragmentSurveysBinding
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote
import com.github.ephelsa.mycareer.ui.utils.BaseFragment
import com.github.ephelsa.mycareer.ui.utils.fullNamePresentation
import com.github.ephelsa.mycareer.ui.utils.hasError
import com.github.ephelsa.mycareer.ui.utils.isLoading
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SurveysFragment : BaseFragment<FragmentSurveysBinding>(), View.OnClickListener {

    private val viewModel: SurveysViewModel by viewModels()
    private lateinit var surveyAdapter: SurveyAdapter
    private val args: SurveysFragmentArgs by navArgs()
    private val directions = SurveysFragmentDirections

    companion object {
        private val TAG = SurveysFragment::class.java.simpleName
    }

    private val surveys: () -> Unit = {
        viewModel.surveys.handleObservable(
            enableDefaultError = false,
            onSuccess = {
                viewModel.verifyListOfSurveys(it.data) // Verify to display or hide recycler
                configureSurveyRecycler(it.data)
            }
        )
    }

    private val userInformation: () -> Unit = {
        viewModel.userInformationByEmail(args.email).handleObservable(
            enableDefaultError = false,
            enableDefaultLoader = false,
            onLoading = {
                binding.userInformation.nameText.isLoading()
            },
            onSuccess = {
                binding.userInformation.nameText.text = it.data.fullNamePresentation()
            },
            onError = {
                binding.userInformation.nameText.hasError(getString(R.string.error_can_not_retrieve_information))
            }
        )
    }

    override fun initializeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSurveysBinding {
        return FragmentSurveysBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uiObservers()
        userInformation()
        surveys()
        bindClickListener()
    }

    private fun bindClickListener() {
        binding.toolbar.logoutButton.setOnClickListener(this)
    }

    private fun uiObservers() {
        viewModel.showSurveyRecycler.observe(viewLifecycleOwner, Observer(::handleShowRecycler))
    }

    private fun handleShowRecycler(show: Boolean) {
        with(binding) {
            surveysRecycler.isVisible = show
            emptyContainer.isVisible = !show
        }
    }

    private fun configureSurveyRecycler(surveysRemote: List<SurveyRemote>) {
        // Configure click actions
        surveyAdapter = SurveyAdapter(surveysRemote) { click ->
            takeSurvey(click.id)
        }

        // Configure recycler
        binding.surveysRecycler.apply {
            adapter = surveyAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SurveysFragment.requireContext())
        }
    }

    private fun takeSurvey(surveyCode: Int) {
        viewModel.surveyWithQuestions(surveyCode)
            .handleObservable(
                onSuccess = {
                    navigate(directions.questionFragment())
                }
            )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.toolbar.logoutButton -> performLogout()
        }
    }

    private fun performLogout() {
        viewModel.deleteStoredSessions
            .handleObservable(
                onSuccess = {
                    navigate(directions.loginFragment())
                }
            )
    }
}
