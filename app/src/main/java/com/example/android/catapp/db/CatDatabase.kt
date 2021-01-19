package com.example.android.catapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Cat::class],version = 3,exportSchema = false)
abstract class CatDatabase : RoomDatabase() {
    abstract val catDao: CatDao

    companion object {
        private var INSTANCE: CatDatabase? = null

        fun getInstance(context: Context): CatDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CatDatabase::class.java,
                        "cat_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance

            }
        }
    }
}
