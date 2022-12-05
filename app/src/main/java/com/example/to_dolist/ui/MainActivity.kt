package com.example.to_dolist.ui

import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.to_dolist.R
import com.example.to_dolist.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        themeMode()
        initNavigationBottom()

        binding.addTask.setOnClickListener {
            openAddTaskLayout()
        }

    }

    private fun initNavigationBottom(){
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController = findNavController(R.id.fragments)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.fragmentLabel.text = destination.label
        }

        bottomNavigation.setupWithNavController(navController)
    }

    private fun openAddTaskLayout(){
        binding.insertUpdateTaskLayout.visibility = View.VISIBLE
        binding.addTask.visibility = View.GONE
        binding.closeTaskLayout.setOnClickListener {
            closeAddTaskLayout()
        }
    }

    private fun closeAddTaskLayout(){
        binding.insertUpdateTaskLayout.visibility = View.GONE
        binding.addTask.visibility = View.VISIBLE
    }

    private fun themeMode(){
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                lightTheme()
            }
            Configuration.UI_MODE_NIGHT_YES -> {
                darkTheme()
            }
        }
    }

    private fun darkTheme(){ }

    private fun lightTheme(){
        binding.inputTask.setBackgroundResource(R.drawable.background_input_light)
        binding.openCalendar.background.setTint(Color.parseColor("#DBDEE2"))
        binding.selectCategory.background.setTint(Color.parseColor("#DBDEE2"))
        binding.saveTask.background.setTint(Color.parseColor("#DBDEE2"))
    }

}