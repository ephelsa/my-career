package com.github.ephelsa.mycareer.delivery.user.local

import com.github.ephelsa.mycareer.data.user.UserLocalDataSource
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler.handleError
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler.handleSuccess
import com.github.ephelsa.mycareer.delivery.user.mapper.toDelivery
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.user.UserLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val userDao: UserDao
) : UserLocalDataSource {
    override suspend fun storeInformation(userLocal: UserLocal): ResourceLocal<Unit> =
        withContext(dispatcher) {
            try {
                val operation = userDao.insert(userLocal.toDelivery())
                handleSuccess(operation)
            } catch (e: Exception) {
                handleError<Unit>(e)
            }
        }

    override suspend fun retrieveInformation(): ResourceLocal<UserLocal> = withContext(dispatcher) {
        try {
            val operation = userDao.selectStored()
            handleSuccess(operation.toDomain())
        } catch (e: Exception) {
            handleError<UserLocal>(e)
        }
    }
}
