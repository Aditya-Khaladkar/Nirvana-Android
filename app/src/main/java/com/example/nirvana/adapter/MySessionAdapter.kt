package com.example.nirvana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nirvana.R
import com.example.nirvana.model.MySessionModel

class MySessionAdapter(private val list: List<MySessionModel>) :
    RecyclerView.Adapter<MySessionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mySessionEventImage: ImageView = itemView.findViewById(R.id.mySessionEventImage)
        val mySessionEventDate: TextView = itemView.findViewById(R.id.mySessionEventDate)
        val mySessionTitle: TextView = itemView.findViewById(R.id.mySessionTitle)
        val mySessionEventAddress: TextView = itemView.findViewById(R.id.mySessionEventAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.created_session_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.mySessionTitle.text = item.title
        holder.mySessionEventAddress.text = item.eventArea
        holder.mySessionEventDate.text = "${item.eventTime} - ${item.eventDate}"

        if (item.eventType == "sports") {
            holder.mySessionEventImage.setBackgroundResource(R.drawable.sports_logo)
        } else if (item.eventType == "music") {
            holder.mySessionEventImage.setBackgroundResource(R.drawable.music_logo)
        }
    }
}