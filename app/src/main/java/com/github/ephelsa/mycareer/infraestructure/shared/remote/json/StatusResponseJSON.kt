package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.squareup.moshi.Json

enum class StatusResponseJSON {
    @Json(name = "success")
    Success,

    @Json(name = "error")
    Error,
}
