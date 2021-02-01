package com.github.ephelsa.mycareer.ui.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.tempToast(message: String, tag: String) {
    Log.v(tag, message)
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
