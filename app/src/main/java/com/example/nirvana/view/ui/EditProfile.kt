package com.example.nirvana.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentEditProfileBinding
import com.example.nirvana.util.ToastMessage
import com.example.nirvana.viewmodel.EditProfileViewModel

class EditProfile : Fragment() {
    lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        // get username from firebase
        val viewModel = ViewModelProvider(this)[EditProfileViewModel::class.java]
        viewModel.getUserName(binding = binding)

        binding.edtProfileBtnSaveProfile.setOnClickListener {
            // getting interests values of checkbox
            val interests = StringBuilder()
            if (binding.edtProfileChkSports.isChecked) {
                interests.append(binding.edtProfileChkSports.text.toString() + " ")
            }
            if (binding.edtProfileChkMusic.isChecked) {
                interests.append(binding.edtProfileChkMusic.text.toString() + " ")
            }
            if (binding.edtProfileChkPhoto.isChecked) {
                interests.append(binding.edtProfileChkPhoto.text.toString() + " ")
            }
            if (binding.edtProfileChkPet.isChecked) {
                interests.append(binding.edtProfileChkPet.text.toString())
            }
            ToastMessage.show(requireContext(), interests.toString())

            // converting string builder to array
            val interestArray = interests.split(" ")

            // calling viewmodel for updating data in database
            viewModel.updateData(
                requireContext(),
                binding.edtProfileUsername.text.toString(),
                binding.edtProfileEmail.text.toString(),
                binding.edtFirstName.text.toString(),
                binding.edtLastName.text.toString(),
                interestArray
            )
        }

        return binding.root
    }
}