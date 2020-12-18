package com.github.ephelsa.mycareer.delivery.auth.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.ephelsa.mycareer.delivery.auth.entity.SessionEntity

@Dao
interface AuthDao {
    @Query("DELETE FROM session")
    suspend fun deleteSessions()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(sessionEntity: SessionEntity)

    @Query("SELECT * FROM session LIMIT 1")
    suspend fun getStoredSession(): SessionEntity
}
