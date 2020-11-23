package com.github.ephelsa.mycareer.infraestructure.shared.remote.interceptor

import okhttp3.logging.HttpLoggingInterceptor

object HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
