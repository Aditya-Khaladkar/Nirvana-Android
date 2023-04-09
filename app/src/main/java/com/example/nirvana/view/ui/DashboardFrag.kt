package com.example.nirvana.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.CarouselAdapter
import com.example.nirvana.databinding.FragmentDashboardBinding
import com.example.nirvana.model.DashboardCarouselModel
import com.example.nirvana.view.fragment.AddSessionScreen
import com.example.nirvana.view.fragment.ProfilePage

class DashboardFrag : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Dashboard Features
        binding.carouselRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.carouselRecyclerView.setHasFixedSize(true)
        val data = listOf(
            DashboardCarouselModel(R.drawable.img_music),
            DashboardCarouselModel(R.drawable.img_sports),
            DashboardCarouselModel(R.drawable.img_photo),
            DashboardCarouselModel(R.drawable.img_pet)
        )
        val adapter = CarouselAdapter(data)
        binding.carouselRecyclerView.adapter = adapter



        // Bottom Nav -> Onclick and Navigation
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFrag -> {
                    loadFragment(DashboardFrag())
                    true
                }
                R.id.addFrag -> {
                    loadFragment(AddSessionScreen())
                    true
                }
                R.id.accountFrag -> {
                    loadFragment(ProfilePage())
                    true
                }
                else -> false
            }
        }

        return binding.root
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction =
            (activity as AppCompatActivity?)!!.supportFragmentManager.beginTransaction()
        transaction.add(R.id.containerLayout, fragment)
        transaction.commit()
    }
}