package com.example.nirvana.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentSignUpBinding
import com.example.nirvana.util.ToastMessage
import com.google.firebase.auth.FirebaseAuth

class SignUpFrag : Fragment() {
    lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.btnSignUp.setOnClickListener {
            auth(
                binding = binding,
                email =  binding.regEmail.text.toString(),
                password = binding.regPassword.text.toString()
            )
        }

        return binding.root
    }

    private fun auth(binding: FragmentSignUpBinding, email: String, password: String) {
        if (
            binding.regEmail.text.toString().isEmpty()
            && binding.regPassword.text.toString().isEmpty()
        ) {
            ToastMessage.show(requireContext(), "Enter Email And Password")
        } else {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Navigation.findNavController(binding.root)
                            .navigate(R.id.action_signUpFrag_to_dashboardFrag)
                    } else {
                        ToastMessage.show(requireContext(), "There was some error")
                    }
                }
        }
    }
}