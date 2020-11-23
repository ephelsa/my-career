package com.github.ephelsa.mycareer.infraestructure.shared.remote

import com.github.ephelsa.mycareer.infraestructure.shared.remote.interceptor.HttpLoggingInterceptor
import okhttp3.OkHttpClient

object OkHttpClientBuild {
    // Place interceptors here
    val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor.httpLoggingInterceptor)
        .build()
}
