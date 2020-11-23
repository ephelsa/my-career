package com.github.ephelsa.mycareer

data class TestSuiteSimple<T>(
    val description: String,
    val got: T,
    val want: T
)
