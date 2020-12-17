package com.github.ephelsa.mycareer.delivery.user.remote

import com.github.ephelsa.mycareer.delivery.shared.remote.json.WrappedResponseJSON
import com.github.ephelsa.mycareer.delivery.user.pojo.UserJSON
import com.github.ephelsa.mycareer.domain.user.UserRemote
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("user/{email}")
    suspend fun userInformation(
        @Path("email") email: String
    ): WrappedResponseJSON<UserRemote, UserJSON>
}
