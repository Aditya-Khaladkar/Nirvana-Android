package com.example.nirvana.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.nirvana.R
import com.example.nirvana.model.NearSessionModel

class NearSessionAdapter(
    private val list: List<NearSessionModel>
) :
    RecyclerView.Adapter<NearSessionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nearSessionEventImage: ImageView = itemView.findViewById(R.id.nearSessionEventImage)
        val nearSessionEventDate: TextView = itemView.findViewById(R.id.nearSessionEventDate)
        val nearSessionTitle: TextView = itemView.findViewById(R.id.nearSessionTitle)
        val nearSessionEventAddress: TextView = itemView.findViewById(R.id.nearSessionEventAddress)
        val nearSessionListCityRL: LinearLayout = itemView.findViewById(R.id.nearSessionListCityRL)
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
        holder.nearSessionEventDate.text = "${item.eventTime} - ${item.eventDate}"

        when (item.eventType) {
            "sports" -> {
                holder.nearSessionEventImage.setBackgroundResource(R.drawable.sports_logo)
            }
            "music" -> {
                holder.nearSessionEventImage.setBackgroundResource(R.drawable.music_logo)
            }
            "photography" -> {
                holder.nearSessionEventImage.setBackgroundResource(R.drawable.photo_logo)
            }
            else -> {
                holder.nearSessionEventImage.setBackgroundResource(R.drawable.pet_logo)
            }
        }

        holder.nearSessionListCityRL.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("imageLink", item.imageLink)
            bundle.putString("title", item.title)
            bundle.putString("eventArea", item.eventArea)
            bundle.putString("eventDescription", item.eventDescription)
            bundle.putString("eventDate", item.eventDate)
            bundle.putString("eventTime", item.eventTime)
            bundle.putString("eventAddress", item.eventAddress)
            bundle.putString("phoneNumber", item.phoneNumber)
            Navigation.findNavController(it)
                .navigate(R.id.action_dashboardFrag_to_eventDetails, bundle)
        }
    }
}