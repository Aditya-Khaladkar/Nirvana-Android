package com.example.nirvana.view.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentEventDetailsBinding

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

        Glide.with(binding.root).load(arguments?.getString("imageLink").toString())
            .into(binding.eventDetailsImage)

        binding.eventDetailTitle.text = title
        binding.eventDetailAddress.text = eventArea
        binding.eventDetailsDesc.text = eventDescription
        binding.eventDetailsDate.text = "Date: ${eventDate.toString()}"
        binding.eventDetailsTime.text = "Time: ${eventTime.toString()}"
        binding.eventDetailsVenue.text = "Venue: ${eventAddress.toString()}"

        return binding.root
    }
}