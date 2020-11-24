package com.github.ephelsa.mycareer.ui.dialog

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.DialogSuccessBinding

class SuccessDialog(
    var dialogListener: DialogListener? = null
) : DialogFragment(), View.OnClickListener, Animator.AnimatorListener {

    private lateinit var binding: DialogSuccessBinding
    var successTitle: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bindClickListener()
        bindLottieListener()
        binView()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSuccessBinding.bind(layoutInflater.inflate(R.layout.dialog_success, null))
        isCancelable = false

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    private fun bindClickListener() {
        binding.closeButton.setOnClickListener(this)
    }

    private fun bindLottieListener() {
        binding.successLottie.addAnimatorListener(this)
    }

    private fun binView() {
        binding.successTitle.text = successTitle ?: ""
    }

    override fun dismiss() {
        binding.successTitle.text = ""
        super.dismiss()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.closeButton -> dismiss()
        }
    }

    override fun onAnimationStart(animation: Animator?) {
        // Not implemented
    }

    override fun onAnimationEnd(animation: Animator?) {
        dialogListener?.onClose(this)
        dismiss()
    }

    override fun onAnimationCancel(animation: Animator?) {
        // Not implemented
    }

    override fun onAnimationRepeat(animation: Animator?) {
        // Not implemented
    }
}
