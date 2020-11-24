package com.github.ephelsa.mycareer.ui.utils

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isErrorEnabled(isEnabled: Boolean, message: String) {
    this.isErrorEnabled = isEnabled
    this.error = if (isEnabled) message else ""
}
