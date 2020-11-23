package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WrappedResponseJSON<T : Any, D : DomainMappable<T>>(
    @field:Json(name = "status") val status: StatusResponseJSON,
    @field:Json(name = "result") val result: D?,
    @field:Json(name = "error") val error: ErrorResponseJSON?
)

@JsonClass(generateAdapter = true)
data class WrappedListResponseJSON<T : Any, D : DomainMappable<T>>(
    @field:Json(name = "status") val status: StatusResponseJSON,
    @field:Json(name = "result") val result: List<D>?,
    @field:Json(name = "error") val error: ErrorResponseJSON?
)
