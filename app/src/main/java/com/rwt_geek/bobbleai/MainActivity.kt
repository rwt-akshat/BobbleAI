package com.rwt_geek.bobbleai

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
* Created By Akshat Rawat on 29th september 2020.
*
* This activity is the main activity of whole application.
* This activity consists of edit text, a button and a recycler view
*
*/
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //declaring global variables
    private lateinit var recyclerView: RecyclerView
    private lateinit var etChat: EditText
    private lateinit var displayBtn: Button
    private lateinit var enTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializing the variables
        recyclerView = findViewById(R.id.recyclerView)
        etChat = findViewById(R.id.etChat)
        displayBtn = findViewById(R.id.displayBtn)
        enTextView = findViewById(R.id.enTextView)

        /*
        * To manage the items of the recycler view, layout manager is used. As the linear vertical list should be shown
        * in particular, that's why linear layout manager is used
        */
        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true
        recyclerView.layoutManager = layoutManager

        // Insert data on button click
        displayBtn.setOnClickListener {
            if (etChat.text.toString().isNotEmpty()) {
                MainViewModel(application).insertData(etChat.text.toString(), 20f)
                etChat.setText("")
            }

            //View Model to separate the implementation logic
            MainViewModel(application).getData().observe(this, {
                val adapter = RecyclerAdapter(this@MainActivity, it)
                recyclerView.adapter = adapter
            })
        }

        // Changing size on long button click
        displayBtn.setOnLongClickListener {
            if (etChat.text.isNotEmpty()) {
                enTextView.visibility = View.VISIBLE //making text view visible
                enTextView.text = etChat.text.toString()

                val startSize = 20f //initial size of text
                val endSize = 30f //end size of the text
                val animationDuration = 1000L // Animation duration in ms

                //Object Animator API to animate the text
                val animator: ValueAnimator =
                    ObjectAnimator.ofFloat(enTextView, "textSize", startSize, endSize)

                animator.duration = animationDuration //setting the animation duration
                animator.start() //starting the animation

                //Giving the delay of 1 second to display the enlarge tex on recycler view
                Handler().postDelayed({

                    /* Coroutines to perform async task
                    *  this will create a background thread and run all database related operation on that thread*/

                    CoroutineScope(Dispatchers.Main).launch {
                        MainViewModel(application).insertData(etChat.text.toString(), 30f)
                        /*
                        *After inserting data into the database empty the input fields
                         */
                        etChat.setText("")
                        enTextView.text = ""
                        enTextView.visibility = View.GONE

                        //Refreshing the recycler view after successfully addition of data in the database
                        MainViewModel(application).getData().observe(this@MainActivity, {
                            val adapter = RecyclerAdapter(this@MainActivity, it)
                            recyclerView.adapter = adapter
                        })
                    }
                }, 1000)

            }
            true
        }
        //Refreshing the recycler view after successfully addition of data in the database
        MainViewModel(application).getData().observe(this, {
            val adapter = RecyclerAdapter(this@MainActivity, it)
            recyclerView.adapter = adapter
        })
    }

    //Creating menu in the application to deleting all the records
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //To handle the menu item click
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                //Deleting all records from the database.
                CoroutineScope(Dispatchers.Main).launch {
                    MainViewModel(application).deleteAll()
                    MainViewModel(application).getData().observe(this@MainActivity, {
                        val adapter = RecyclerAdapter(this@MainActivity, it)
                        recyclerView.adapter = adapter
                    })
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}