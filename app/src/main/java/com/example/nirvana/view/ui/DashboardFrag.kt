package com.example.nirvana.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentDashboardBinding
import com.example.nirvana.util.ToastMessage
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
        val carouselData = listOf(
            SlideModel(R.drawable.auth, "sports"),
            SlideModel(R.drawable.img_music, "music"),
            SlideModel(R.drawable.img_photo, "photography"),
            SlideModel(R.drawable.img_pet, "petting")
        )

        binding.imageSlider.setImageList(carouselData)

        binding.imageSlider.setItemClickListener(object : ItemClickListener{
            override fun onItemSelected(position: Int) {
                ToastMessage.show(requireContext(), position.toString())
            }
        })

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