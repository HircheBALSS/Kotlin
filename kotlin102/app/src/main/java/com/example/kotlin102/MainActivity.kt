package com.example.kotlin102

import SecondFragmentViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var secondFragmentViewModel: SecondFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        secondFragmentViewModel = ViewModelProvider( this).get(SecondFragmentViewModel::class.java)
        secondFragmentViewModel.retrieveCountries()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as? NavHostFragment
        navController = navHostFragment?.navController ?: throw IllegalStateException("NavHostFragment is not found")

        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}