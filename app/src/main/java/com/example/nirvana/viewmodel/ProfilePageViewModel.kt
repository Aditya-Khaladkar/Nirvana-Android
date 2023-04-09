package com.example.nirvana.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nirvana.databinding.FragmentProfilePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class ProfilePageViewModel : ViewModel() {

    @SuppressLint("SetTextI18n")
    fun getUserName(
        binding: FragmentProfilePageBinding
    ) = viewModelScope.launch {
        val documentReference: DocumentReference =
            FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
        documentReference.addSnapshotListener { value, error ->
            binding.profileTxtUsername.text = "Hi, ${value?.getString("username")}"
        }
    }
}