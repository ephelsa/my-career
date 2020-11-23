package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponseJSON(
    @field:Json(name = "message") val message: String,
    @field:Json(name = "details") val details: String
)
