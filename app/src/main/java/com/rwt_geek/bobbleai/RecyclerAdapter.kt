package com.rwt_geek.bobbleai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rwt_geek.bobbleai.database.BobbleEntity

/*
* Recycler View Adapter to class to handle the items in the recycler view.
*/
class RecyclerAdapter(private val context: Context, private val chatList: List<BobbleEntity>) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView =
            itemView.findViewById(R.id.textView) // text view from the chat_layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.chat_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text =
            chatList[position].chatData //bind the main chat data and show in the recycler view
        holder.textView.textSize =
            chatList[position].textSize //bind the text size with the recycler view
    }

    override fun getItemCount(): Int {
        return chatList.size //returning the chats count
    }
}