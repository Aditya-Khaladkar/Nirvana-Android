package com.example.nirvana.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.CategoryAdapter
import com.example.nirvana.databinding.FragmentDashboardBinding
import com.example.nirvana.model.CategoryModel
import com.example.nirvana.util.ToastMessage

class DashboardFrag : Fragment() {
    lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Setting Toolbar and side nav bar
        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.sideNavToolbar)

        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // set up nav bar icon
        (activity as AppCompatActivity?)!!.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)

        // Dashboard Features
        category(binding = binding)

        return binding.root
    }

    private fun category(binding: FragmentDashboardBinding) {
        binding.categoryRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.setHasFixedSize(true)
        val data = listOf(
            CategoryModel(R.raw.sports, "sports"),
            CategoryModel(R.raw.music, "music"),
            CategoryModel(R.raw.pet, "petting")
        )
        val adapter = CategoryAdapter(data)
        binding.categoryRecyclerView.adapter = adapter
    }
}