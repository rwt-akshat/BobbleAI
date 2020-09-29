package com.rwt_geek.bobbleai.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/*
* Database class to handle and binds all the entities in the database.
*/
@Database(
    entities = [BobbleEntity::class],
    version = 1
)
//abstract class for bobble(app) database
abstract class BobbleDatabase : RoomDatabase() {
    abstract fun bobbleDao(): BobbleDao

    // companion object to create only one instance of the database in the app.
    companion object {

        @Volatile
        private var instance: BobbleDatabase? = null

        fun getInstance(context: Context): BobbleDatabase = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): BobbleDatabase {
            return Room.databaseBuilder(context, BobbleDatabase::class.java, "bobbleDatabase")
                .build()
        }
    }
}