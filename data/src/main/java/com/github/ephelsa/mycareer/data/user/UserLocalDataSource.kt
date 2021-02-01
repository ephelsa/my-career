package com.github.ephelsa.mycareer.data.user

import com.github.ephelsa.mycareer.domain.shared.ResourceLocal
import com.github.ephelsa.mycareer.domain.user.UserLocal

interface UserLocalDataSource {
    suspend fun storeInformation(userLocal: UserLocal): ResourceLocal<Unit>

    suspend fun retrieveInformation(): ResourceLocal<UserLocal>
}
