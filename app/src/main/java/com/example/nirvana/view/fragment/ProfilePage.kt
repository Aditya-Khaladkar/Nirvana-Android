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

        editProfile(binding = binding, context = requireContext())

        return binding.root
    }

    private fun editProfile(binding: FragmentProfilePageBinding, context: Context) {
        // Pick gender
        val gender = arrayOf("Select gender", "Male", "Female")
        val arrayAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, gender)
        binding.spinnerGender.adapter = arrayAdapter

        binding.spinnerGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                profileGender = gender[position]
                if (profileGender == "Male" || profileGender == "Female") {
                    ToastMessage.show(requireContext(), profileGender)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        // Events
        binding.edtPickDateOfBirth.setOnClickListener {
            calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            datePickerDialog = DatePickerDialog(
                context,
                { datePicker, year, month, day -> binding.edtPickDateOfBirth.setText(day.toString() + "/" + (month + 1) + "/" + year) },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }
    }
}