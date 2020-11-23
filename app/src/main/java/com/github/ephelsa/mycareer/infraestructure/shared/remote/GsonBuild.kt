package com.github.ephelsa.mycareer.infraestructure.shared.remote

import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

object GsonBuild {

    val gson = GsonBuilder().create()
    val gsonConverterFactory = GsonConverterFactory.create(gson)
}
