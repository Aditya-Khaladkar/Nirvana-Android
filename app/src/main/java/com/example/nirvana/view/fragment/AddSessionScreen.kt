package com.example.nirvana.view.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentAddSessionScreenBinding
import com.example.nirvana.viewmodel.AddSessionScreenViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class AddSessionScreen : Fragment() {
   lateinit var binding: FragmentAddSessionScreenBinding
   lateinit var mAuth: FirebaseAuth

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
        binding = FragmentAddSessionScreenBinding.inflate(inflater, container, false)

        // init firebase auth
        mAuth = FirebaseAuth.getInstance()

        // get email from firebase auth panel
        binding.edEventEmail.setText(mAuth.currentUser?.email)
//        viewModel = ViewModelProvider(this)[AddSessionScreenViewModel::class.java]
//        viewModel.getEmailFromFirebase(binding, mAuth)

        // select event date
        binding.txtEventDate.setOnClickListener {
            calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            datePickerDialog = DatePickerDialog(
                requireContext(),
                { datePicker, year, month, day -> binding.txtEventDate.text =
                    day.toString() + "/" + (month + 1) + "/" + year },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        return binding.root
    }
}