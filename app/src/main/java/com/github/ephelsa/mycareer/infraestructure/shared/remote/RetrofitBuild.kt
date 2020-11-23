package com.github.ephelsa.mycareer.infraestructure.shared.remote

import com.github.ephelsa.mycareer.BuildConfig
import retrofit2.Retrofit

object RetrofitBuild {
    val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonBuild.gsonConverterFactory)
        .baseUrl(BuildConfig.API_URL)
        .client(OkHttpClientBuild.okHttpClient)
        .build()
}
