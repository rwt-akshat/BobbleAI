package com.rwt_geek.bobbleai

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rwt_geek.bobbleai.database.bobbleEntity
import com.rwt_geek.bobbleai.database.bobbledatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application):AndroidViewModel(application) {
    private var mutableLiveData = MutableLiveData<List<bobbleEntity>>()
    val database = bobbledatabase.getInstance(application).bobbleDao()

    fun getData():MutableLiveData<List<bobbleEntity>>{
        CoroutineScope(Dispatchers.Main).launch {
            val chatList = database.getAllChats()
            mutableLiveData.value = chatList
        }
        return mutableLiveData
    }
}