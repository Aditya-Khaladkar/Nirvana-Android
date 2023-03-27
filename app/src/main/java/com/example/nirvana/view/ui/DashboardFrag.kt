package com.example.nirvana.view.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.CategoryAdapter
import com.example.nirvana.databinding.FragmentDashboardBinding
import com.example.nirvana.model.CategoryModel
import com.example.nirvana.view.fragment.AddSessionScreen
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class DashboardFrag : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

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

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        actionBarDrawerToggle = ActionBarDrawerToggle(
            context as Activity?,
            binding.dashboardDrawerLayout,
            R.string.nav_open,
            R.string.nav_close
        )

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        binding.dashboardDrawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.sideNavToolbar)


        // to make the Navigation drawer icon always appear on the action bar
        (activity as AppCompatActivity?)!!.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (activity as AppCompatActivity?)!!.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24)

        // Dashboard Features
        category(binding = binding)

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
                else -> false
            }
        }

        // Side nav bar
        binding.nvView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navSignOut -> authSignOut()
            }
            true
        }

        return binding.root
    }

    private fun category(binding: FragmentDashboardBinding) {
        binding.categoryRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.categoryRecyclerView.setHasFixedSize(true)
        val data = listOf(
            CategoryModel(R.raw.sports, "sports"),
            CategoryModel(R.raw.music, "music"),
            CategoryModel(R.raw.pet, "petting"),
            CategoryModel(R.raw.photo, "photography")
        )
        val adapter = CategoryAdapter(data)
        binding.categoryRecyclerView.adapter = adapter
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction =
            (activity as AppCompatActivity?)!!.supportFragmentManager.beginTransaction()
        transaction.add(R.id.containerLayout, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}