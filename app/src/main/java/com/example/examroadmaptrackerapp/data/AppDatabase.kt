package com.example.examroadmaptrackerapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.examroadmaptrackerapp.data.entities.SubjectEntity
import com.example.examroadmaptrackerapp.data.entities.TopicEntity
import com.example.examroadmaptrackerapp.data.entities.TaskEntity
import com.example.examroadmaptrackerapp.data.entities.StudySessionEntity
import com.example.examroadmaptrackerapp.data.entities.DailyLogEntity
import com.example.examroadmaptrackerapp.data.entities.AnalysisReportEntity

//Singleton Architecture
@Database(
    entities = [
        SubjectEntity::class,
        TopicEntity::class,
        TaskEntity::class,
        StudySessionEntity::class,
        DailyLogEntity::class,
        AnalysisReportEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun trackerDao(): TrackerDAO

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "Academic_tracker_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}