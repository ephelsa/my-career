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
import androidx.fragment.app.FragmentManager
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.databinding.DialogLoaderBinding

class LoaderDialog : DialogFragment() {

    private var binding: DialogLoaderBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogLoaderBinding.bind(layoutInflater.inflate(R.layout.dialog_loader, null))
        isCancelable = false

        return AlertDialog.Builder(requireActivity())
            .setView(binding?.root)
            .create()
    }
}
