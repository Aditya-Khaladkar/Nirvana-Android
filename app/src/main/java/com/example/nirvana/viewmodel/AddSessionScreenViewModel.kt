package com.example.nirvana.viewmodel

import android.content.Context
import android.net.Uri
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentAddSessionScreenBinding
import com.example.nirvana.util.AuthDialogueBox
import com.example.nirvana.util.FirebaseAPI
import com.example.nirvana.util.ToastMessage
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
        title: String,
        hostName: String,
        fullName: String,
        email: String,
        phoneNumber: String,
        eventAddress: String,
        eventArea: String,
        eventCity: String,
        eventType: String,
        eventDate: String,
        eventTime: String,
        eventDescription: String,
        uri: Uri
    ) = viewModelScope.launch {
        // Initialize database
        val firebaseDatabase = FirebaseDatabase.getInstance()
        val reference: StorageReference = FirebaseStorage.getInstance().reference

        val ref = reference.child("eventImages").child(FirebaseAPI.auth.currentUser?.uid.toString())
            .child("$title.png")
        ref.putFile(uri).addOnSuccessListener {
            ref.downloadUrl.addOnSuccessListener {

                // Map values to insert in database
                val map = HashMap<String, Any>()
                map["imageLink"] = it.toString()
                map["title"] = title
                map["hostName"] = hostName
                map["fullName"] = fullName
                map["email"] = email
                map["phoneNumber"] = phoneNumber
                map["eventAddress"] = eventAddress
                map["eventArea"] = eventArea
                map["eventCity"] = eventCity
                map["eventType"] = eventType
                map["eventDate"] = eventDate
                map["eventTime"] = eventTime
                map["eventDescription"] = eventDescription

                FirebaseFirestore.getInstance().collection("all_events")
                    .document()
                    .set(map)
                    .addOnCompleteListener {
                        AuthDialogueBox.dialog.dismiss()
                        ToastMessage.show(context, "event added")
                    }
                    .addOnFailureListener {
                        ToastMessage.show(context, it.toString())
                    }
            }
        }.addOnFailureListener {

        }
    }
}