package com.github.ephelsa.mycareer.ui.surveys

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
            bindLoader(surveyRemote)
            bindClickListener()
        }

        private fun CardSurveyItemBinding.bindLoader(surveyRemote: SurveyRemote) {
            val total = surveyRemote.totalQuestions
            val answered = surveyRemote.questionsAnswered

            if (answered == null) {
                completeProgress.isVisible = false
            } else {
                val isComplete = total == answered
                completeProgress.min = 0F
                completeProgress.max = total.toFloat()
                completeProgress.progress = answered.toFloat()
                // Label text
                completeProgress.labelText = if (isComplete)
                    root.context.getString(R.string.success_survey_complete)
                else
                    root.context.getString(R.string.label_questions_answered, answered, total)

                // Progress color
                completeProgress.highlightView.color = if (isComplete)
                    ContextCompat.getColor(root.context, R.color.green_700)
                else
                    ContextCompat.getColor(root.context, R.color.silver)

                // Label color text color
                completeProgress.labelColorInner = if (isComplete)
                    ContextCompat.getColor(root.context, R.color.black)
                else
                    ContextCompat.getColor(root.context, R.color.white)
            }
        }

        private fun CardSurveyItemBinding.bindClickListener() {
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
