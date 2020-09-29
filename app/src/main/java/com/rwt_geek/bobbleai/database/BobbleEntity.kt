package com.rwt_geek.bobbleai.database

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
* Entity(Table) to store all the chats with chat size
*/
@Entity(
    tableName = "bobble"
)
data class BobbleEntity(
    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0, //primary key
    val chatData: String, // field to store chat data
    val textSize: Float // field to store the size of the text
)