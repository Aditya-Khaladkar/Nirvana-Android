package com.example.nirvana.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nirvana.R
import com.example.nirvana.databinding.FragmentCategoriesListViewBinding

class CategoriesListView : Fragment() {
    lateinit var binding: FragmentCategoriesListViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesListViewBinding.inflate(inflater, container, false)



        return binding.root
    }
}