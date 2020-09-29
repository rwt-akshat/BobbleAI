package com.rwt_geek.bobbleai.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bobble"
)
data class bobbleEntity(
    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0,
    val chatData: String
)