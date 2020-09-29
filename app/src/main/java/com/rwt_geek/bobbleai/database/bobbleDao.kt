package com.rwt_geek.bobbleai.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface bobbleDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChats(chat:bobbleEntity):Long

    @Query("SELECT * FROM bobble")
    suspend fun getAllChats(): List<bobbleEntity>
}