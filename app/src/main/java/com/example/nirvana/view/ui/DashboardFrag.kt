package com.example.nirvana.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentDashboardBinding
import com.example.nirvana.view.fragment.AddSessionScreen
import com.example.nirvana.view.fragment.ProfilePage
import com.google.firebase.auth.FirebaseAuth

class DashboardFrag : Fragment() {
    lateinit var binding: FragmentDashboardBinding

    private fun authSignOut() {
        FirebaseAuth.getInstance().signOut()
        Navigation.findNavController(binding.root).navigate(R.id.action_dashboardFrag_to_signInFrag)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        // Dashboard Features
        binding.lottieViewSports.setAnimation(R.raw.sports)
        binding.lottieViewMusic.setAnimation(R.raw.music)
        binding.lottieViewPetting.setAnimation(R.raw.pet)
        binding.lottieViewPhoto.setAnimation(R.raw.photo)

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