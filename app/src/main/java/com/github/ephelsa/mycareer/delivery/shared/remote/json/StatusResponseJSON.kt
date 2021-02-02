package com.github.ephelsa.mycareer.delivery.shared.remote.json

import com.github.ephelsa.mycareer.delivery.shared.mapper.DomainMappable
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.google.gson.annotations.SerializedName

enum class StatusResponseJSON : DomainMappable<StatusRemote> {
    @SerializedName("success")
    Success {
        override fun toDomain(): StatusRemote = StatusRemote.Success
    },

    @SerializedName("error")
    Error {
        override fun toDomain(): StatusRemote = StatusRemote.Error
    },
}
