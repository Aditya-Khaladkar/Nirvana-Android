package com.example.nirvana.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentSignUpBinding
import com.example.nirvana.util.AuthDialogueBox
import com.example.nirvana.util.ToastMessage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class SignUpFrag : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.btnSignUp.setOnClickListener {

            // Launching Dialogue Box
            AuthDialogueBox.setProgressDialog(requireContext(), "Please wait while signing up...")

            auth(
                binding = binding,
                email = binding.regEmail.text.toString(),
                password = binding.regPassword.text.toString()
            )
        }

        return binding.root
    }

    private fun auth(binding: FragmentSignUpBinding, email: String, password: String) {
        if (
            binding.regUsername.text.toString().isEmpty()
            && binding.regEmail.text.toString().isEmpty()
            && binding.regPassword.text.toString().isEmpty()
        ) {
            ToastMessage.show(requireContext(), "Enter Email, Password, username")
        } else {

            // Cloud Storage
           val map = hashMapOf(
               "username" to binding.regUsername.text.toString(),
               "email" to binding.regEmail.text.toString()
           )

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {

                        //lifecycleScope.launch {

                            FirebaseFirestore.getInstance().collection("users")
                                .document(FirebaseAuth.getInstance().currentUser!!.uid)
                                .set(map)
                                .addOnSuccessListener {

                                    // Dismiss dialogue box
                                    AuthDialogueBox.dialog.dismiss()

                                    // Navigating to home page
                                    Navigation.findNavController(binding.root)
                                        .navigate(R.id.action_signUpFrag_to_dashboardFrag)
                                }
                                .addOnFailureListener {
                                    ToastMessage.show(requireContext(), "data cannot be posted")
                                }

                       // }
                    } else {
                        ToastMessage.show(requireContext(), "There was some error")
                    }
                }
        }
    }
}