package com.example.nirvana.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nirvana.databinding.FragmentAddSessionScreenBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AddSessionScreenViewModel : ViewModel() {

//    fun getEmailFromFirebase(
//        binding: FragmentAddSessionScreenBinding,
//        mAuth: FirebaseAuth
//    ) = viewModelScope.launch {
//        binding.edEventEmail.setText(mAuth.currentUser?.email)
//    }

    fun pushEventDataToFirebase() {

    }
}