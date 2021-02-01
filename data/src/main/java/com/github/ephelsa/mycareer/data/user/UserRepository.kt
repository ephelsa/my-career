package com.github.ephelsa.mycareer.data.user

import com.github.ephelsa.mycareer.domain.user.UserLocal

class UserRepository(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
) {
    suspend fun informationByEmail(email: String) = userRemoteDataSource.informationByEmail(email)

    suspend fun storeInformation(userLocal: UserLocal) =
        userLocalDataSource.storeInformation(userLocal)

    suspend fun retrieveInformation() = userLocalDataSource.retrieveInformation()
}
