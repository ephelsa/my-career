package com.github.ephelsa.mycareer.delivery.auth.local

import com.github.ephelsa.mycareer.data.auth.AuthLocalDataSource
import com.github.ephelsa.mycareer.delivery.auth.mapper.toDelivery
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler.handleError
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler.handleSuccess
import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthLocalRepository @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val authDao: AuthDao
) : AuthLocalDataSource {
    override suspend fun deleteStoredSessions(): ResourceLocal<Unit> = withContext(dispatcher) {
        try {
            val operation = authDao.deleteSessions()
            handleSuccess(operation)
        } catch (e: Exception) {
            handleError<Unit>(e)
        }
    }

    override suspend fun storeSession(authCredentialLocal: AuthCredentialLocal): ResourceLocal<Unit> =
        withContext(dispatcher) {
            try {
                val operation = authDao.insertSession(authCredentialLocal.toDelivery())
                handleSuccess(operation)
            } catch (e: Exception) {
                handleError<Unit>(e)
            }
        }

    override suspend fun getStoredSession(): ResourceLocal<AuthCredentialLocal> =
        withContext(dispatcher) {
            try {
                val operation = authDao.getStoredSession().toDomain()
                handleSuccess(operation)
            } catch (e: Exception) {
                handleError<AuthCredentialLocal>(e)
            }
        }
}
