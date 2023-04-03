package com.example.nirvana.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentAddSessionScreenBinding
import com.example.nirvana.viewmodel.AddSessionScreenViewModel
import com.google.firebase.auth.FirebaseAuth


class AddSessionScreen : Fragment() {
   lateinit var binding: FragmentAddSessionScreenBinding
   lateinit var mAuth: FirebaseAuth

   // init viewmodel
   lateinit var viewModel: AddSessionScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddSessionScreenBinding.inflate(inflater, container, false)

        // init firebase auth
        mAuth = FirebaseAuth.getInstance()

        // get email from firebase auth panel
        binding.edEventEmail.setText(mAuth.currentUser?.email)
//        viewModel = ViewModelProvider(this)[AddSessionScreenViewModel::class.java]
//        viewModel.getEmailFromFirebase(binding, mAuth)

        return binding.root
    }
}