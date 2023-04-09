package com.example.nirvana.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nirvana.databinding.FragmentEditProfileBinding
import com.example.nirvana.databinding.FragmentProfilePageBinding
import com.example.nirvana.util.FirebaseAPI
import com.example.nirvana.util.ToastMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class EditProfileViewModel : ViewModel() {
    @SuppressLint("SetTextI18n")
    fun getUserName(
        binding: FragmentEditProfileBinding
    ) = viewModelScope.launch {
        val documentReference: DocumentReference =
            FirebaseAPI.firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
        documentReference.addSnapshotListener { value, error ->
            binding.edtProfileUsername.setText(value?.getString("username"))
            binding.edtProfileEmail.setText(value?.getString("email"))
            binding.edtFirstName.setText(value?.getString("firstname"))
            binding.edtLastName.setText(value?.getString("lastname"))
        }
    }

    fun updateData(
        context: Context,
        username: String,
        email: String,
        firstName: String,
        lastName: String,
        interests: Any
    ) = viewModelScope.launch {
        // Map values to insert in database
        val map = HashMap<String, Any>()
        map["email"] = email
        map["username"] = username
        map["firstname"] = firstName
        map["lastname"] = lastName
        map["interests"] = interests

        FirebaseAPI.firestore.collection("users")
            .document(FirebaseAPI.auth.currentUser?.uid.toString())
            .set(map)
            .addOnSuccessListener {
                ToastMessage.show(context, "profile updated")
            }
    }
}