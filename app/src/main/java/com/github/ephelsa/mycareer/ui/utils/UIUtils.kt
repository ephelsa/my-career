package com.github.ephelsa.mycareer.ui.utils

import androidx.core.content.res.ResourcesCompat
import com.github.ephelsa.mycareer.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isErrorEnabled(isEnabled: Boolean, message: String) {
    this.isErrorEnabled = isEnabled
    this.error = if (isEnabled) message else ""
}

fun TextInputLayout.isLoading(isLoading: Boolean, message: String? = null) {
    this.editText?.isEnabled = !isLoading
    this.isHelperTextEnabled = isLoading
    this.helperText = if (isLoading) message else ""
}
