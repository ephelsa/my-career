package com.github.ephelsa.mycareer.ui.utils

import android.widget.TextView
import com.github.ephelsa.mycareer.R
import com.github.ephelsa.mycareer.domain.user.UserLocal
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isLoading(isLoading: Boolean, message: String? = null) {
    this.editText?.isEnabled = !isLoading
    this.isHelperTextEnabled = isLoading
    this.helperText = if (isLoading) message else ""
}

fun TextInputLayout.hasError(isEnabled: Boolean, message: String) {
    this.isErrorEnabled = isEnabled
    this.error = if (isEnabled) message else ""
}

fun TextView.isLoading() {
    this.text = context.getString(R.string.loading_user_info)
}

fun TextView.hasError(message: String) {
    this.setTextColor(context.getColor(R.color.red_700))
    this.text = message
}

fun UserLocal.fullNamePresentation(): String {
    val mSecondName = secondName.takeIf { !secondName.isNullOrEmpty() }?.get(0)?.plus(".") ?: ""
    val names = "$firstName $mSecondName".trim()
    val surnames = "$firstSurname $secondSurname".trim()

    return "$names $surnames"
}
