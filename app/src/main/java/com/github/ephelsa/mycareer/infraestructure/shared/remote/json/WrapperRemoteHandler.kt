package com.github.ephelsa.mycareer.infraestructure.shared.remote.json

import com.github.ephelsa.mycareer.domain.shared.ErrorRemote
import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.shared.StatusRemote
import com.github.ephelsa.mycareer.domain.shared.WrappedRemote
import com.github.ephelsa.mycareer.infraestructure.shared.mapper.toDomain
import com.github.ephelsa.mycareer.infraestructure.shared.remote.MoshiBuild
import retrofit2.HttpException

object WrapperRemoteHandler {

    fun <T : Any> handleSuccess(wrappedRemote: WrappedRemote<T>): ResourceRemote.Success<T> {
        return ResourceRemote.Success(wrappedRemote.status, wrappedRemote.result!!)
    }

    fun <T : Any> handleError(e: Exception): ResourceRemote.Error<T> {
        return when (e) {
            is HttpException -> parseHttpException(e)
            else -> ResourceRemote.Error<T>(
                StatusRemote.UnexpectedError,
                ErrorRemote("Unexpected error", e.toString())
            )
        }
    }

    private fun <T : Any> parseHttpException(e: HttpException): ResourceRemote.Error<T> {
        return try {
            val data = e.response()?.errorBody()!!.let {
                MoshiBuild.moshi.adapter(WrappedResponseJSON::class.java).fromJson(it.string())
            }
            ResourceRemote.Error<T>(
                status = data!!.status.toDomain(),
                error = data.error!!.toDomain()
            )
        } catch (e: Exception) {
            ResourceRemote.Error<T>(
                StatusRemote.UnexpectedError,
                ErrorRemote("Unexpected error", e.toString())
            )
        }
    }
}
