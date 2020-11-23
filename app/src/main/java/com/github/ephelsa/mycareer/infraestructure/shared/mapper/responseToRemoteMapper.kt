package com.github.ephelsa.mycareer.infraestructure.shared.mapper

import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.shared.WrappedRemote
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.ErrorResponseJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.StatusResponseJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.infraestructure.shared.remote.json.WrappedResponseJSON

fun ErrorResponseJSON.toDomain() = ErrorRemote(
    message = message,
    details = details
)

fun StatusResponseJSON.toDomain(): StatusRemote = when (this) {
    StatusResponseJSON.Success -> StatusRemote.Success
    StatusResponseJSON.Error -> StatusRemote.Error
}

fun <T : Any, D : DomainMappable<T>> WrappedResponseJSON<T, D>.toDomain() = WrappedRemote(
    status = status.toDomain(),
    result = result?.toDomain(),
    error = error?.toDomain()
)

fun <T : Any, D : DomainMappable<T>> WrappedListResponseJSON<T, D>.toDomain() =
    WrappedRemote<List<T>>(
        status = status.toDomain(),
        result = result?.map { it.toDomain() },
        error = error?.toDomain()
    )
