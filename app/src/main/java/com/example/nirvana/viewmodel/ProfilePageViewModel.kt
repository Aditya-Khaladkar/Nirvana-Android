package com.example.nirvana.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class ProfilePageViewModel : ViewModel() {

    // getting username

    private val _username = MutableLiveData<String>().apply {
        val documentReference: DocumentReference =
            FirebaseFirestore.getInstance().collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
        documentReference.addSnapshotListener { v, err ->
            value = v?.getString("username")
        }
    }

    val username: LiveData<String> = _username

//    // getting phone number
//
//    private val _phnNo = MutableLiveData<String>().apply {
//        val documentReference: DocumentReference =
//            FirebaseFirestore.getInstance().collection("users")
//                .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
//        documentReference.addSnapshotListener { v, err ->
//            value = v?.getString("phoneNumber")
//        }
//    }
//
//    val phnNo: LiveData<String> = _phnNo
}