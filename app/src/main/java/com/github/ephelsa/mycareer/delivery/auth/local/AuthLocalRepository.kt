package com.github.ephelsa.mycareer.delivery.auth.local

import com.github.ephelsa.mycareer.data.auth.AuthLocalDataSource
import com.github.ephelsa.mycareer.delivery.auth.mapper.toDomain
import com.github.ephelsa.mycareer.delivery.auth.mapper.toRoom
import com.github.ephelsa.mycareer.delivery.shared.local.LocalHandler
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
    override suspend fun storeSession(authCredentialLocal: AuthCredentialLocal): ResourceLocal<Unit> =
        withContext(dispatcher) {
            try {
                val operation = authDao.storeSessionCredentials(authCredentialLocal.toRoom())
                LocalHandler.handleSuccess(operation)
            } catch (e: Exception) {
                LocalHandler.handleError<Unit>(e)
            }
        }

    override suspend fun getStoredSession(): ResourceLocal<AuthCredentialLocal> =
        withContext(dispatcher) {
            try {
                val operation = authDao.getStoredSessionCredentials().toDomain()
                LocalHandler.handleSuccess(operation)
            } catch (e: Exception) {
                LocalHandler.handleError<AuthCredentialLocal>(e)
            }
        }
}
