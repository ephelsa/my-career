package com.github.ephelsa.mycareer.ui.utils

import java.util.*

fun <T> List<T>.match(compare: String, contain: (T) -> String): T? {
    val pCompare = compare.toLowerCase(Locale.getDefault())
    return this.find {
        val pContain = contain(it).toLowerCase(Locale.getDefault())
        pCompare == pContain
    }
}
