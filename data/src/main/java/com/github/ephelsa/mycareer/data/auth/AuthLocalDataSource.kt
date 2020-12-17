package com.github.ephelsa.mycareer.data.auth

import com.github.ephelsa.mycareer.domain.auth.AuthCredentialLocal
import com.github.ephelsa.mycareer.domain.shared.ResourceLocal

interface AuthLocalDataSource {
    suspend fun storeSession(authCredentialLocal: AuthCredentialLocal): ResourceLocal<Unit>

    suspend fun getStoredSession(): ResourceLocal<AuthCredentialLocal>
}
