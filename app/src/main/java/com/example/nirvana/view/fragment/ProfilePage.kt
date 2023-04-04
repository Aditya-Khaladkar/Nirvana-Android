package com.example.nirvana.view.fragment

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentProfilePageBinding
import com.example.nirvana.util.ToastMessage
import java.util.*

class ProfilePage : Fragment() {
    lateinit var binding: FragmentProfilePageBinding
    lateinit var profileGender: String

    // define Choose Date of birth
    lateinit var datePickerDialog: DatePickerDialog
    var year = 0
    var month = 0
    var dayOfMonth = 0
    lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfilePageBinding.inflate(layoutInflater, container, false)

        binding.profileTxtEditProfile.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_dashboardFrag_to_editProfile)
        }

        return binding.root
    }
}
