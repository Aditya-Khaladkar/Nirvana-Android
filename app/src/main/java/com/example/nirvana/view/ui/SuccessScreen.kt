package com.example.nirvana.view.ui

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.nirvana.R

class SuccessScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Handler().postDelayed(Runnable {
            Navigation.findNavController(requireView()).navigate(R.id.action_successScreen_to_profilePage)
        },2000)

        return inflater.inflate(R.layout.fragment_success_screen, container, false)
    }
}