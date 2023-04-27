package com.example.nirvana.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.MySessionAdapter
import com.example.nirvana.adapter.NearSessionAdapter
import com.example.nirvana.adapter.ScheduledSessionAdapter
import com.example.nirvana.databinding.FragmentProfilePageBinding
import com.example.nirvana.model.MySessionModel
import com.example.nirvana.model.NearSessionModel
import com.example.nirvana.model.ScheduledSessionModel
import com.example.nirvana.util.FirebaseAPI
import com.example.nirvana.viewmodel.ProfilePageViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ProfilePage : Fragment() {
    lateinit var binding: FragmentProfilePageBinding
    lateinit var nearSessionAdapter: MySessionAdapter
    private var nearSessionArray: ArrayList<MySessionModel>? = null

    // variables for scheduled sessions
    lateinit var scheduledSessionAdapter: ScheduledSessionAdapter
    private var scheduledSessionArray: ArrayList<ScheduledSessionModel>? = null

    // variables for drop down
    var createdSessionCounter = 0
    var scheduledSessionCounter = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfilePageBinding.inflate(layoutInflater, container, false)

        val db = FirebaseFirestore.getInstance()

        nearSessionArray = ArrayList()

        // Calling viewmodel
        val viewModel = ViewModelProvider(this)[ProfilePageViewModel::class.java]
        viewModel.username.observe(viewLifecycleOwner, Observer {
            binding.profileTxtUsername.text = "Hi, $it"
        })

        // login from drop down of sessions
        binding.profileCreatedSessionFL.setOnClickListener {
            createdSessionCounter++
            if (createdSessionCounter % 2 != 0) {
                binding.mySessionRecyclerView.visibility = View.GONE
            } else {
                binding.mySessionRecyclerView.visibility = View.VISIBLE
            }
        }

        binding.profileScheduledSessionFL.setOnClickListener {
            scheduledSessionCounter++
            if (scheduledSessionCounter % 2 != 0) {
                binding.scheduledSessionRecyclerView.visibility = View.GONE
            } else {
                binding.scheduledSessionRecyclerView.visibility = View.VISIBLE
            }
        }

        binding.profileTxtEditProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_dashboardFrag_to_editProfile)
        }

        // setting up recyclerview for created session
        binding.mySessionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mySessionRecyclerView.setHasFixedSize(true)


        nearSessionAdapter = MySessionAdapter(nearSessionArray!!, requireContext())

        binding.mySessionRecyclerView.adapter = nearSessionAdapter

            db.collection("all_events")
            .whereEqualTo("userUID", FirebaseAPI.auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: MySessionModel? = d.toObject(MySessionModel::class.java)
                        nearSessionArray!!.add(c!!)
                    }
                    nearSessionAdapter.notifyDataSetChanged()
                }
            }.addOnFailureListener { // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(context, "Fail to get the data.", Toast.LENGTH_SHORT)
                    .show()
            }

        // setting up recyclerview for scheduled session
        binding.scheduledSessionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.scheduledSessionRecyclerView.setHasFixedSize(true)

        scheduledSessionArray = ArrayList()

        scheduledSessionAdapter = ScheduledSessionAdapter(scheduledSessionArray!!)

        binding.scheduledSessionRecyclerView.adapter = scheduledSessionAdapter

        db.collection("scheduled_events")
            .whereEqualTo("userUID", FirebaseAPI.auth.currentUser?.uid)
            .get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                if (!queryDocumentSnapshots.isEmpty) {
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        val c: ScheduledSessionModel? = d.toObject(ScheduledSessionModel::class.java)
                        scheduledSessionArray!!.add(c!!)
                    }
                    scheduledSessionAdapter.notifyDataSetChanged()
                }
            }.addOnFailureListener { // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
                Toast.makeText(context, "Fail to get the data.", Toast.LENGTH_SHORT)
                    .show()
            }


        return binding.root
    }
}
