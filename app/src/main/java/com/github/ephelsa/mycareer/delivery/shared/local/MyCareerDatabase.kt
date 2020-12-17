package com.github.ephelsa.mycareer.delivery.shared.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.ephelsa.mycareer.delivery.auth.entity.SessionEntity
import com.github.ephelsa.mycareer.delivery.auth.local.AuthDao
import javax.inject.Singleton

@Singleton
@Database(entities = [SessionEntity::class], version = 1, exportSchema = false)
abstract class MyCareerDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "my-career"

        @Volatile
        private var instance: MyCareerDatabase? = null

        fun getInstance(context: Context): MyCareerDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MyCareerDatabase {
            return Room.databaseBuilder(context, MyCareerDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }

    abstract fun authDao(): AuthDao
}
