package com.example.nirvana.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.MySessionAdapter
import com.example.nirvana.adapter.NearSessionAdapter
import com.example.nirvana.databinding.FragmentProfilePageBinding
import com.example.nirvana.model.MySessionModel
import com.example.nirvana.model.NearSessionModel
import com.example.nirvana.util.FirebaseAPI
import com.example.nirvana.viewmodel.ProfilePageViewModel
import com.google.firebase.firestore.FirebaseFirestore

class ProfilePage : Fragment() {
    lateinit var binding: FragmentProfilePageBinding
    lateinit var nearSessionAdapter: MySessionAdapter
    private var nearSessionArray: ArrayList<MySessionModel>? = null

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
        viewModel.getUserName(binding = binding)

        binding.profileTxtEditProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_dashboardFrag_to_editProfile)
        }

        // setting up recyclerview for created session
        binding.mySessionRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mySessionRecyclerView.setHasFixedSize(true)


        nearSessionAdapter = MySessionAdapter(nearSessionArray!!)

        binding.mySessionRecyclerView.adapter = nearSessionAdapter

        db.collection("my_events")
            .document(FirebaseAPI.auth.currentUser?.uid.toString())
            .collection(FirebaseAPI.auth.currentUser?.uid.toString())
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

        return binding.root
    }
}
