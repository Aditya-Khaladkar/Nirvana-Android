package com.example.nirvana.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentProfilePageBinding

class ProfilePage : Fragment() {
    lateinit var binding: FragmentProfilePageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfilePageBinding.inflate(layoutInflater, container, false)



        return binding.root
    }
}