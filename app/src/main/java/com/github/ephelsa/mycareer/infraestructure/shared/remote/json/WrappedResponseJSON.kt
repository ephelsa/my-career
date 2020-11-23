package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.github.ephelsa.mycareer.infraestructure.shared.mapper.DomainMappable
import com.google.gson.annotations.SerializedName

data class WrappedResponseJSON<Domain : Any, JsonMappable : DomainMappable<Domain>>(
    @SerializedName("status") val status: StatusResponseJSON,
    @SerializedName("result") val result: JsonMappable?,
    @SerializedName("error") val error: ErrorResponseJSON?
)

data class WrappedListResponseJSON<Domain : Any, JsonMappable : DomainMappable<Domain>>(
    @SerializedName("status") val status: StatusResponseJSON,
    @SerializedName("result") val result: List<JsonMappable>?,
    @SerializedName("error") val error: ErrorResponseJSON?
)

data class WrapperResponseSimplyJSON<T : Any>(
    @SerializedName("status") val status: StatusResponseJSON,
    @SerializedName("result") val result: T?,
    @SerializedName("error") val error: ErrorResponseJSON?
)
