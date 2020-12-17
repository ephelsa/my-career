package com.github.ephelsa.mycareer.data.user

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource
) {
    suspend fun informationByEmail(email: String) = userRemoteDataSource.informationByEmail(email)
}
