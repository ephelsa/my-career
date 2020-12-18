package com.github.ephelsa.mycareer.delivery.auth.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.ephelsa.mycareer.delivery.auth.entity.SessionEntity

@Dao
interface AuthDao {
    @Query("DELETE FROM SessionEntity")
    suspend fun deleteStoredSessions()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun storeSessionCredentials(sessionEntity: SessionEntity)

    @Query("SELECT * FROM SessionEntity LIMIT 1")
    suspend fun getStoredSessionCredentials(): SessionEntity
}
