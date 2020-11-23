package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.google.gson.annotations.SerializedName

data class WrappedResponseJSON<T : Any, D : DomainMappable<T>>(
    @SerializedName("status") val status: StatusResponseJSON,
    @SerializedName("result") val result: D?,
    @SerializedName("error") val error: ErrorResponseJSON?
)

data class WrappedListResponseJSON<T : Any, D : DomainMappable<T>>(
    @SerializedName("status") val status: StatusResponseJSON,
    @SerializedName("result") val result: List<D>?,
    @SerializedName("error") val error: ErrorResponseJSON?
)

data class WrapperResponseSimplyJSON<T : Any>(
    @SerializedName("status") val status: StatusResponseJSON,
    @SerializedName("result") val result: T?,
    @SerializedName("error") val error: ErrorResponseJSON?
)
