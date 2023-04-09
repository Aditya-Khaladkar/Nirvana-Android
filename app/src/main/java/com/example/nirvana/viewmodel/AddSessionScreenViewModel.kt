package com.example.nirvana.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nirvana.databinding.FragmentAddSessionScreenBinding
import com.example.nirvana.util.ToastMessage
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch

class AddSessionScreenViewModel : ViewModel() {

//    fun getEmailFromFirebase(
//        binding: FragmentAddSessionScreenBinding,
//        mAuth: FirebaseAuth
//    ) = viewModelScope.launch {
//        binding.edEventEmail.setText(mAuth.currentUser?.email)
//    }

    fun pushEventDataToFirebase(
        context: Context,
        hostName: String,
        fullName: String,
        email: String,
        phoneNumber: String,
        eventAddress: String,
        eventCity: String,
        eventType: String,
        eventDate: String,
        eventTime: String,
        eventDescription: String
    ) = viewModelScope.launch {
        // Initialize database
        val firebaseDatabase = FirebaseDatabase.getInstance()

        // Map values to insert in database
        val map = HashMap<String, Any>()
        map["hostName"] = hostName
        map["fullName"] = fullName
        map["email"] = email
        map["phoneNumber"] = phoneNumber
        map["eventAddress"] = eventAddress
        map["eventCity"] = eventCity
        map["eventDate"] = eventDate
        map["eventTime"] = eventTime
        map["eventDescription"] = eventDescription

        firebaseDatabase.getReference("events").child(eventType).push()
            .setValue(map)
            .addOnCompleteListener {
                ToastMessage.show(context, "event added")
            }
            .addOnFailureListener {
                ToastMessage.show(context, it.toString())
            }
    }
}