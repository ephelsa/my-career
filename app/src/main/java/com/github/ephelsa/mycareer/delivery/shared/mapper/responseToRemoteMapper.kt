package com.github.ephelsa.mycareer.delivery.shared.mapper

import com.github.ephelsa.mycareer.delivery.shared.remote.json.ErrorResponseJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedListResponseJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedResponseJSON
import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrapperResponseSimplyJSON
import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.WrappedRemote

fun ErrorResponseJSON.toDomain() = ErrorRemote(
    message = message,
    details = details
)

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

fun <T : Any> WrapperResponseSimplyJSON<T>.toDomain() = WrappedRemote<T>(
    status = status.toDomain(),
    result = result,
    error = error?.toDomain()
)
