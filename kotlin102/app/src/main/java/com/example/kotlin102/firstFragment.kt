package com.example.kotlin102

import RecyclerViewAdapter
import SecondFragmentViewModel
import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FirstFragment : Fragment() {
    private lateinit var secondFragmentViewModel: SecondFragmentViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        secondFragmentViewModel = ViewModelProvider(requireActivity()).get(SecondFragmentViewModel::class.java)
        val countries = secondFragmentViewModel.countries.value

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = countries?.let { RecyclerViewAdapter(it) }
        recyclerView.adapter = adapter

        val button = view.findViewById<Button>(R.id.next)
        button.setOnClickListener { findNavController().navigate(R.id.action_firstFragment_to_secondFragment) }

        return view
    }

}

