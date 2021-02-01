package com.github.ephelsa.mycareer.delivery.shared.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.github.ephelsa.mycareer.delivery.auth.entity.SessionEntity
import com.github.ephelsa.mycareer.delivery.auth.local.AuthDao
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionAnswerEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.QuestionEntity
import com.github.ephelsa.mycareer.delivery.survey.entity.UserAnswerEntity
import com.github.ephelsa.mycareer.delivery.survey.local.SurveyDao
import com.github.ephelsa.mycareer.delivery.user.entity.UserInfoEntity
import com.github.ephelsa.mycareer.delivery.user.local.UserDao

@Database(
    entities = [
        SessionEntity::class,
        QuestionEntity::class,
        QuestionAnswerEntity::class,
        UserInfoEntity::class,
        UserAnswerEntity::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(Converters::class)
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
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun authDao(): AuthDao
    abstract fun surveyDao(): SurveyDao
    abstract fun userDao(): UserDao
}
