package com.example.nirvana.view.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentSplashScreenBinding

class SplashScreenFrag : Fragment() {
    lateinit var binding: FragmentSplashScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)

        Handler().postDelayed(Runnable {
            Navigation
                .findNavController(binding.root)
                .navigate(R.id.action_splashScreenFrag_to_signUpFrag)
        },2000)

        return binding.root
    }
}