package com.example.nirvana.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nirvana.R
import com.example.nirvana.adapter.NearSessionAdapter
import com.example.nirvana.databinding.FragmentCategoriesListViewBinding
import com.example.nirvana.model.NearSessionModel
import com.google.firebase.firestore.FirebaseFirestore

class CategoriesListView : Fragment() {
    lateinit var binding: FragmentCategoriesListViewBinding

    // recalling session model and adapter
    lateinit var nearSessionAdapter: NearSessionAdapter
    private var nearSessionArray: ArrayList<NearSessionModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesListViewBinding.inflate(inflater, container, false)

        val db = FirebaseFirestore.getInstance()

        nearSessionArray = ArrayList()

        val category: String = arguments?.getString("category").toString()

        Log.d("@debug", "onCreateView: $category")

        // setting up recycler view
        binding.categoryListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.categoryListRecyclerView.setHasFixedSize(true)

        nearSessionAdapter = NearSessionAdapter(nearSessionArray!!)

        binding.categoryListRecyclerView.adapter = nearSessionAdapter

        db.collection("all_events").whereEqualTo("eventType", category).get()
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

        return binding.root
    }
}