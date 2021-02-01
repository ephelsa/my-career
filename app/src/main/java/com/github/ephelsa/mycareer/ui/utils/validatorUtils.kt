package com.github.ephelsa.mycareer.ui.utils

fun String.isTextValid() = Regex("^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]*\$").matches(this)

fun String.isNumberValid() = Regex("^[0-9]+\$").matches(this)

fun String.isDateValid() = Regex("^[0-9]{2}/[0-9]{2}/[0-9]{4}\$").matches(this)
