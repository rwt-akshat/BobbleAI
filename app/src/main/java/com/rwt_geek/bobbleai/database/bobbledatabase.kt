package com.rwt_geek.bobbleai.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities =[bobbleEntity::class],
    version = 1
)
abstract class bobbledatabase:RoomDatabase() {
    abstract fun bobbleDao():bobbleDao

    companion object {

        @Volatile
        private var instance: bobbledatabase? = null

        fun getInstance(context: Context): bobbledatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): bobbledatabase {
            return Room.databaseBuilder(context, bobbledatabase::class.java, "bobbleDatabase")
                .build()
        }
    }
}