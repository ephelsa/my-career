package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.google.gson.annotations.SerializedName


enum class StatusResponseJSON {
    @SerializedName("success")
    Success,

    @SerializedName("error")
    Error,
}
