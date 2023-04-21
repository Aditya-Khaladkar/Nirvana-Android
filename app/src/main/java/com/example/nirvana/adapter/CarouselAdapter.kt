package com.example.nirvana.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.nirvana.R
import com.example.nirvana.model.DashboardCarouselModel

class CarouselAdapter(private val list: List<DashboardCarouselModel>) :
    RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.carouselImg)
        //val txt: TextView = itemView.findViewById(R.id.carouselTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.carousel_card, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.img.setImageResource(item.img)
        //holder.txt.text = item.txt
        holder.img.setOnClickListener {
            Log.d("@debug", "onBindViewHolder: $position")
            val bundle = Bundle()
            when (position) {
                0 -> {
                    bundle.putString("category", "music")
                }
                1 -> {
                    bundle.putString("category", "sports")
                }
                2 -> {
                    bundle.putString("category", "photography")
                }
                3 -> {
                    bundle.putString("category", "petting")
                }
            }
            Navigation.findNavController(it)
                .navigate(R.id.action_dashboardFrag_to_categoriesListView, bundle)
        }
    }
}