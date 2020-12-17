package com.github.ephelsa.mycareer.ui.surveys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.CardSurveyItemBinding
import com.github.ephelsa.mycareer.domain.survey.SurveyRemote

class SurveyAdapter(
    private val surveysRemote: List<SurveyRemote>,
    private val takeSurveyClick: (SurveyRemote) -> Unit
) : RecyclerView.Adapter<SurveyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurveyAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_survey_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SurveyAdapter.ViewHolder, position: Int) {
        holder.bindView(surveysRemote[position])
    }

    override fun getItemCount(): Int = surveysRemote.size

    /**
     * View Holder for the RecyclerView.
     */
    inner class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val binding = CardSurveyItemBinding.bind(itemView)

        fun bindView(surveyRemote: SurveyRemote): Unit = with(binding) {
            nameText.text = surveyRemote.name
            if (surveyRemote.description != null) descriptionText.text = surveyRemote.description
            else descriptionText.isVisible = false
            container.isEnabled = surveyRemote.isActive
            takeSurveyButton.isEnabled = surveyRemote.isActive
            bindClickListener()
        }

        private fun bindClickListener(): Unit = with(binding) {
            takeSurveyButton.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(v: View?): Unit = with(binding) {
            when (v) {
                takeSurveyButton -> performTakeSurvey()
            }
        }

        private fun performTakeSurvey() {
            takeSurveyClick(surveysRemote[adapterPosition])
        }
    }
}
