package com.rwt_geek.bobbleai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rwt_geek.bobbleai.database.bobbleEntity

class RecyclerAdapter(private val context:Context,private val chatList:List<bobbleEntity>):RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){
    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val textView:TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.chat_layout,parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = chatList[position].chatData
    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}