package com.example.nirvana.view.fragment

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.nirvana.databinding.FragmentAddSessionScreenBinding
import com.example.nirvana.util.AuthDialogueBox
import com.example.nirvana.util.ToastMessage
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
    private val REQUEST_IMAGE = 100

    lateinit var eventCity: String
    lateinit var eventHobby: String

    lateinit var imageUri: Uri

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

        eventDetails(binding)

        // calling view model
        val viewModel = ViewModelProvider(this)[AddSessionScreenViewModel::class.java]

        binding.btnAddSession.setOnClickListener {
            AuthDialogueBox.setProgressDialog(requireContext(), "adding your session")

            if (imageUri != null) {

                viewModel.pushEventDataToFirebase(
                    requireContext(),
                    binding.edEventTitle.text.toString(),
                    binding.edEventHost.text.toString(),
                    binding.edEventFullName.text.toString(),
                    binding.edEventEmail.text.toString(),
                    binding.edEventPhoneNumber.text.toString(),
                    binding.edEventAddress.text.toString(),
                    binding.edEventArea.text.toString(),
                    eventCity,
                    eventHobby,
                    binding.txtEventDate.text.toString(),
                    binding.txtEventTime.text.toString(),
                    binding.edEventDescription.text.toString(),
                    imageUri
                )
            } else {
                ToastMessage.show(requireContext(), "upload image")
            }
        }

        return binding.root
    }

    private fun eventDetails(binding: FragmentAddSessionScreenBinding) {
        // select session image from gallery
        binding.eventImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, REQUEST_IMAGE)
        }

        // select session city
        val cityArray = listOf("Select City", "Pune", "Bangalore", "Mumbai", "Hyderabad")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cityArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEventCity.adapter = adapter

        binding.spinnerEventCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedCity = p0?.getItemAtPosition(p2).toString()
                if (selectedCity == "Select City") {

                }
                eventCity = selectedCity
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }


        // select event type ( hobby )
        val hobbyArray = listOf("Select Hobby","sports", "music", "photography", "petting")
        val hobbyAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, hobbyArray)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerEventType.adapter = hobbyAdapter

        binding.spinnerEventType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedHobby = p0?.getItemAtPosition(p2).toString()
                if (selectedHobby == "Select Hobby") {

                }
                eventHobby = selectedHobby
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        // select event date
        binding.txtEventDate.setOnClickListener {
            calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            datePickerDialog = DatePickerDialog(
                requireContext(),
                { datePicker, year, month, day ->
                    binding.txtEventDate.text =
                        day.toString() + "/" + (month + 1) + "/" + year
                },
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.show()
        }

        // select event time
        binding.txtEventTime.setOnClickListener {
            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting our hour, minute.
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            // on below line we are initializing
            // our Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                context,
                { view, hourOfDay, minute ->
                    // on below line we are setting selected
                    // time in our text view.
                    binding.txtEventTime.text = "$hourOfDay:$minute"
                },
                hour,
                minute,
                false
            )
            // at last we are calling show to
            // display our time picker dialog.
            timePickerDialog.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.data!!
            binding.eventImage.setImageURI(imageUri)
        }
    }
}