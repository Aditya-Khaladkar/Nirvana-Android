package com.example.nirvana.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nirvana.R
import com.example.nirvana.model.MySessionModel
import com.example.nirvana.util.ToastMessage
import com.google.firebase.firestore.FirebaseFirestore

class MySessionAdapter(private val list: List<MySessionModel>, private val context: Context) :
    RecyclerView.Adapter<MySessionAdapter.ViewHolder>() {

    val db = FirebaseFirestore.getInstance()
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mySessionEventImage: ImageView = itemView.findViewById(R.id.mySessionEventImage)
        val mySessionEventDate: TextView = itemView.findViewById(R.id.mySessionEventDate)
        val mySessionTitle: TextView = itemView.findViewById(R.id.mySessionTitle)
        val mySessionEventAddress: TextView = itemView.findViewById(R.id.mySessionEventAddress)
        val btnDeleteSession: Button = itemView.findViewById(R.id.btnDeleteSession)
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

        holder.btnDeleteSession.setOnClickListener {
            db.collection("all_events").whereEqualTo("title", item.title)
                .get().addOnCompleteListener {
                    if (it.isSuccessful && !it.result.isEmpty) {
                        val docSnap = it.result.documents[0]
                        val docId: String = docSnap.id
                        db.collection("all_events")
                            .document(docId)
                            .delete()
                            .addOnSuccessListener {
                                ToastMessage.show(context, "Event Deleted Successfully")
                            }
                    }
                }
        }

        when (item.eventType) {
            "sports" -> {
                holder.mySessionEventImage.setBackgroundResource(R.drawable.sports_logo)
            }
            "music" -> {
                holder.mySessionEventImage.setBackgroundResource(R.drawable.music_logo)
            }
            "photography" -> {
                holder.mySessionEventImage.setBackgroundResource(R.drawable.photo_logo)
            }
            else -> {
                holder.mySessionEventImage.setBackgroundResource(R.drawable.pet_logo)
            }
        }
    }
}