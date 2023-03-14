package com.example.nirvana.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.example.nirvana.R
import com.example.nirvana.model.CategoryModel

class CategoryAdapter(private val list: List<CategoryModel>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lottieView: LottieAnimationView = itemView.findViewById(R.id.lottie_view)
        val categoryName: TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_list, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = list[position]
        holder.lottieView.setAnimation(item.lottieList)
        holder.lottieView.playAnimation()
        holder.categoryName.text = item.categoryName
    }
}