package com.rwt_geek.bobbleai

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.rwt_geek.bobbleai.database.BobbleDatabase
import com.rwt_geek.bobbleai.database.BobbleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* All the logic is implemented here in the view model
* This class is connecting the database with the main UI of the android
*/
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //Live Data to show real time changes in the android UI and to handle some configuration bugs
    private var mutableLiveData = MutableLiveData<List<BobbleEntity>>()

    //Initializing the database to perform operations mentioned in the database DAO
    private val database = BobbleDatabase.getInstance(application).bobbleDao()

    //Function to insert data in the database
    fun insertData(chat: String, size: Float) {
        CoroutineScope(Dispatchers.Main).launch {
            database.insertChats(BobbleEntity(0, chat, size))
        }
    }

    //Function to get all data from the database
    fun getData(): MutableLiveData<List<BobbleEntity>> {
        CoroutineScope(Dispatchers.Main).launch {
            val chatList = database.getAllChats()
            mutableLiveData.value = chatList
        }
        return mutableLiveData
    }

    //Function to delete all data from the database
    fun deleteAll() {
        CoroutineScope(Dispatchers.Main).launch {
            database.deleteAll()
        }
    }
}