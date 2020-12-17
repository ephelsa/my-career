package com.github.ephelsa.mycareer.data.user

import com.github.ephelsa.mycareer.domain.shared.ResourceRemote
import com.github.ephelsa.mycareer.domain.user.UserRemote

interface UserRemoteDataSource {
    suspend fun informationByEmail(email: String): ResourceRemote<UserRemote>
}
