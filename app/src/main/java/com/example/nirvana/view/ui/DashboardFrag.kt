package com.example.nirvana.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.CarouselAdapter
import com.example.nirvana.adapter.NearSessionAdapter
import com.example.nirvana.databinding.FragmentDashboardBinding
import com.example.nirvana.model.DashboardCarouselModel
import com.example.nirvana.model.NearSessionModel
import com.example.nirvana.view.fragment.AddSessionScreen
import com.example.nirvana.view.fragment.ProfilePage
import com.google.firebase.firestore.FirebaseFirestore

class DashboardFrag : Fragment() {
    lateinit var binding: FragmentDashboardBinding
    lateinit var nearSessionAdapter: NearSessionAdapter
    private var nearSessionArray: ArrayList<NearSessionModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val db = FirebaseFirestore.getInstance()

        nearSessionArray = ArrayList()

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

        // What's in your city recyclerView
        binding.nearSessionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.nearSessionRecyclerView.setHasFixedSize(true)

        nearSessionAdapter = NearSessionAdapter(nearSessionArray!!)

        binding.nearSessionRecyclerView.adapter = nearSessionAdapter

        db.collection("all_events").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: NearSessionModel? = d.toObject(NearSessionModel::class.java)
                        nearSessionArray!!.add(c!!)
                    }
                    nearSessionAdapter.notifyDataSetChanged()
                }
            }.addOnFailureListener { // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(context, "Fail to get the data.", Toast.LENGTH_SHORT)
                    .show()
            }


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