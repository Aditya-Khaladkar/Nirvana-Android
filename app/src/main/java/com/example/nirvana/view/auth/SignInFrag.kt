package com.example.nirvana.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentSignInBinding
import com.example.nirvana.util.ToastMessage
import com.google.firebase.auth.FirebaseAuth

class SignInFrag : Fragment() {
    lateinit var binding: FragmentSignInBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        // Handling OnClick
        binding.txtNewUser.setOnClickListener {
            Navigation.findNavController(binding.root)
                .navigate(R.id.action_signInFrag_to_signUpFrag)
        }

        binding.btnSignIn.setOnClickListener {
            auth(
                binding = binding,
                email = binding.logEmail.text.toString(),
                password = binding.logPassword.text.toString()
            )
        }

        return binding.root
    }

    private fun auth(binding: FragmentSignInBinding, email: String, password: String) {
        if (
            binding.logEmail.text.toString().isEmpty()
            && binding.logPassword.text.toString().isEmpty()
        ) {
            ToastMessage.show(requireContext(), "Enter Email And Password")
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_signInFrag_to_dashboardFrag)
                    } else {
                        ToastMessage.show(requireContext(), "There was some error")
                    }
                }
        }
    }
}