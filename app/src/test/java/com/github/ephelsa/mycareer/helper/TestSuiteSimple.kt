package com.github.ephelsa.mycareer.helper

data class TestSuiteSimple<T>(
    val description: String,
    val got: T,
    val want: T
)
