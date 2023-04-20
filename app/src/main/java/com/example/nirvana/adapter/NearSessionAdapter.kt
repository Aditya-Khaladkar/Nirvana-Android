package com.example.nirvana.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nirvana.R
import com.example.nirvana.model.NearSessionModel
import com.example.nirvana.view.ui.DashboardFrag
import com.example.nirvana.view.ui.EventDetails

class NearSessionAdapter(
    private val list: List<NearSessionModel>
) :
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

        holder.nearSessionImage.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("imageLink", item.imageLink)
            bundle.putString("title", item.title)
            bundle.putString("eventArea", item.eventArea)
            bundle.putString("eventDescription", item.eventDescription)
            bundle.putString("eventDate", item.eventDate)
            bundle.putString("eventTime", item.eventTime)
            bundle.putString("eventAddress", item.eventAddress)
            Navigation.findNavController(it)
                .navigate(R.id.action_dashboardFrag_to_eventDetails, bundle)
        }
    }
}