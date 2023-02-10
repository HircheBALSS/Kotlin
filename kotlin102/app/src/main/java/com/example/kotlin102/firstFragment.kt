package com.example.kotlin102

import SecondFragmentViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        val button = view.findViewById<Button>(R.id.next)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
        }
        return view
    }
}

