package com.example.nirvana.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentProfilePageBinding
import com.example.nirvana.viewmodel.ProfilePageViewModel

class ProfilePage : Fragment() {
    lateinit var binding: FragmentProfilePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfilePageBinding.inflate(layoutInflater, container, false)

        // Calling viewmodel
        val viewModel = ViewModelProvider(this)[ProfilePageViewModel::class.java]
        viewModel.getUserName(binding = binding)

        binding.profileTxtEditProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_dashboardFrag_to_editProfile)
        }

        return binding.root
    }
}
