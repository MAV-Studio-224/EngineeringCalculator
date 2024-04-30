package com.hfad.engineeringcalculator

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.synchronized

@Database(entities = [ECEntity::class], version = 1)
abstract class ECDatabase: RoomDatabase() {
    abstract val ecDao: ECDAO

    companion object {
        @Volatile
        private var INSTANCE: ECDatabase? = null

        fun getInstance(context: Context): ECDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ECDatabase::class.java,
                        "ec_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}