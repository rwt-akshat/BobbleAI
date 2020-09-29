package com.rwt_geek.bobbleai

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rwt_geek.bobbleai.database.bobbleEntity
import com.rwt_geek.bobbleai.database.bobbledatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var etChat:EditText
    lateinit var displayBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        etChat = findViewById(R.id.etChat)
        displayBtn = findViewById(R.id.displayBtn)

        val database = bobbledatabase.getInstance(this).bobbleDao()
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager

        displayBtn.setOnClickListener {
            if (etChat.text.toString().isNotEmpty()) {
                CoroutineScope(Dispatchers.Main).launch {
                    database.insertChats(bobbleEntity(0, etChat.text.toString()))
                    etChat.setText("")
                }
            }
            MainViewModel(application).getData().observe(this, Observer {
                val adapter = RecyclerAdapter(this@MainActivity, it)
                recyclerView.adapter = adapter
            })
        }
        displayBtn.setOnLongClickListener(object:View.OnLongClickListener{
            override fun onLongClick(p0: View?): Boolean {
                return true
            }
        })

        MainViewModel(application).getData().observe(this, Observer {
            val adapter = RecyclerAdapter(this@MainActivity, it)
            recyclerView.adapter = adapter
        })
    }
}