package com.github.ephelsa.mycareer.delivery.user.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.ephelsa.mycareer.delivery.user.entity.UserInfoEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userInfoEntity: UserInfoEntity)

    @Query("SELECT * FROM user_info LIMIT 1")
    suspend fun selectStored(): UserInfoEntity
}
