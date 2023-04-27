package com.example.nirvana.view.ui

import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.nirvana.databinding.FragmentEventDetailsBinding
import com.example.nirvana.util.FirebaseAPI
import com.example.nirvana.util.ToastMessage
import com.example.nirvana.viewmodel.ProfilePageViewModel
import com.google.firebase.firestore.FirebaseFirestore

class EventDetails : Fragment() {
    lateinit var binding: FragmentEventDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment

        // getting data from adapter to detail fragment
        val title: String = arguments?.getString("title").toString()
        val eventArea: String = arguments?.getString("eventArea").toString()
        val eventDescription: String = arguments?.getString("eventDescription").toString()
        val eventDate: String = arguments?.getString("eventDate").toString()
        val eventTime: String = arguments?.getString("eventTime").toString()
        val eventAddress: String = arguments?.getString("eventAddress").toString()
        val imageLink: String = arguments?.getString("imageLink").toString()
        val phoneNumber: String = arguments?.getString("phoneNumber").toString()

        Glide.with(binding.root).load(imageLink)
            .into(binding.eventDetailsImage)

        binding.eventDetailTitle.text = title
        binding.eventDetailAddress.text = eventArea
        binding.eventDetailsDesc.text = eventDescription
        binding.eventDetailsDate.text = "Date: ${eventDate.toString()}"
        binding.eventDetailsTime.text = "Time: ${eventTime.toString()}"
        binding.eventDetailsVenue.text = "Venue: ${eventAddress.toString()}"
        binding.eventContactDetails.text = "Contact Details: ${phoneNumber.toString()}"

        // mapping events for adding them under scheduled events
        val map = HashMap<String, Any>()
        map["title"] = title
        map["eventArea"] = eventArea
        map["eventDescription"] = eventDescription
        map["eventDate"] = eventDate
        map["eventTime"] = eventTime
        map["eventAddress"] = eventAddress
        map["imageLink"] = imageLink
        map["userUID"] = FirebaseAPI.auth.currentUser?.uid.toString()

        binding.btnEventDetailAdd.setOnClickListener {
            // adding sessions to local profile
            FirebaseFirestore.getInstance().collection("scheduled_events")
                .document()
                .set(map)
                .addOnCompleteListener {
                    ToastMessage.show(requireContext(), "event added")
                }
                .addOnFailureListener {
                    ToastMessage.show(requireContext(), it.toString())
                }
        }

        //val viewModel = ViewModelProvider(this)[ProfilePageViewModel::class.java]


        // click on get contact button to send session details to email id
//        binding.btnEventDetailGet.setOnClickListener {
            // calling profile viewmodel to get username
//            var userName = ""
//            var phnNo = ""
//
//            viewModel.username.observe(viewLifecycleOwner, Observer {
//                userName += it
//            })
//
//            viewModel.phnNo.observe(viewLifecycleOwner, Observer {
//                phnNo += it
//            })
//
//            Log.d("@debug", "onCreateView: $userName")
//
//
////            // construct SMS message
//            val message = "Event Details: $title at $eventArea on $eventDate"
//
//            val connectPersonDetail =
//                "$userName wants to connect with you for Event: $title, $userName's Mobile number is $phnNo"
//
////             send SMS message
//            val smsManager = SmsManager.getDefault()
//            smsManager.sendTextMessage(
//                "+91${phoneNumber}",
//                null,
//                message + "\n" + connectPersonDetail,
//                null,
//                null
//            )
//
//            // display success message
//            Toast.makeText(
//                context,
//                "Connection have been sent to Event Host vie SMS, they will soon contact you",
//                Toast.LENGTH_SHORT
//            ).show()
       // }

        return binding.root
    }
}