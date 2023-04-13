package com.example.nirvana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nirvana.R
import com.example.nirvana.model.NearSessionModel

class NearSessionAdapter(private val list: List<NearSessionModel>) :
    RecyclerView.Adapter<NearSessionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nearSessionImage: ImageView = itemView.findViewById(R.id.nearSessionImage)
        val nearSessionTitle: TextView = itemView.findViewById(R.id.nearSessionTitle)
        val nearSessionEventAddress: TextView = itemView.findViewById(R.id.nearSessionEventAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.near_session_list_city, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.nearSessionTitle.text = item.title
        holder.nearSessionEventAddress.text = item.eventArea
        Glide.with(holder.nearSessionImage.context).load(item.imageLink)
            .into(holder.nearSessionImage)
    }
}