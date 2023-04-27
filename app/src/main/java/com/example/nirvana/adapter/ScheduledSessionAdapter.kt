package com.example.nirvana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nirvana.R
import com.example.nirvana.model.ScheduledSessionModel

class ScheduledSessionAdapter(private val list: List<ScheduledSessionModel>) : RecyclerView.Adapter<ScheduledSessionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mySessionEventImage: ImageView = itemView.findViewById(R.id.scheduledSessionEventImage)
        val mySessionEventDate: TextView = itemView.findViewById(R.id.scheduledSessionEventDate)
        val mySessionTitle: TextView = itemView.findViewById(R.id.scheduledSessionTitle)
        val mySessionEventAddress: TextView = itemView.findViewById(R.id.scheduledSessionEventAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.scheduled_session_list, parent, false)
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
    }
}