package com.github.ephelsa.mycareer.ui.dialog

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
import com.github.ephelsa.mycareer.databinding.DialogErrorBinding

class ErrorDialog : DialogFragment(), View.OnClickListener {

    private lateinit var binding: DialogErrorBinding
    var errorTitle: String? = null
    var errorMessage: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        bindClickListener()
        binView()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogErrorBinding.bind(layoutInflater.inflate(R.layout.dialog_error, null))
        isCancelable = false

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    private fun bindClickListener() {
        binding.closeButton.setOnClickListener(this)
    }

    private fun binView() {
        binding.errorTitleText.text = errorTitle ?: ""
        binding.errorText.text = errorMessage ?: ""
    }

    override fun dismiss() {
        binding.errorTitleText.text = ""
        binding.errorText.text = ""
        super.dismiss()
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.closeButton -> dismiss()
        }
    }
}
