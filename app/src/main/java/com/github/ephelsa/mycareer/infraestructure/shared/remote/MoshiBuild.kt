package com.github.ephelsa.mycareer.infraestructure.shared.remote

import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory

object MoshiBuild {
    val moshi: Moshi = Moshi.Builder().build()
    val moshiConverterFactory: MoshiConverterFactory = MoshiConverterFactory.create(moshi)
}
