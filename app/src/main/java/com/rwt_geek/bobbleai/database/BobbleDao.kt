package com.rwt_geek.bobbleai.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/*
* To use application data using room persistent library, data access objects(DAO) are used,
* This includes variety of methods which provides the proper abstraction over the sqLite functions like insertion, deletion,,
* updation,etc.
*/
@Dao
interface BobbleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chat: BobbleEntity): Long // function to insert chats in the database

    @Query("SELECT * FROM bobble")
    suspend fun getAllChats(): List<BobbleEntity> // Getting all chats from the database to expose to recycler view

    @Query("DELETE FROM bobble")
    suspend fun deleteAll() // Deleting all rows from the database
}