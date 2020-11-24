package com.github.ephelsa.mycareer.ui.utils

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.hasError(isEnabled: Boolean, message: String) {
    this.isErrorEnabled = isEnabled
    this.error = if (isEnabled) message else ""
}

fun TextInputLayout.isLoading(isLoading: Boolean, message: String? = null) {
    this.editText?.isEnabled = !isLoading
    this.isHelperTextEnabled = isLoading
    this.helperText = if (isLoading) message else ""
}
