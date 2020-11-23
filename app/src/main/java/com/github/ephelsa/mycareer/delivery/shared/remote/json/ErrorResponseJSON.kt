package com.github.ephelsa.mycareer.delivery.shared.remote.json

import com.google.gson.annotations.SerializedName

data class ErrorResponseJSON(
    @SerializedName("message") val message: String,
    @SerializedName("details") val details: String
)
