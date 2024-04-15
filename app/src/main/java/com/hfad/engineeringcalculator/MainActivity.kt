package com.hfad.engineeringcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.hfad.engineeringcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.menu.setupWithNavController(navController)

        setContentView(view)
    }
}