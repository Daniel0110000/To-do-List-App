package com.example.to_dolist.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.to_dolist.R
import com.example.to_dolist.databinding.ActivityMainBinding
import com.example.to_dolist.viewModel.TaskViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        themeMode()
        initNavigationBottom()
        viewModelInstances()

        binding.task = viewModel

        binding.addTask.setOnClickListener {
            openAddTaskLayout()
        }

        binding.moreOptions.setOnClickListener {
            val iconMenu = findViewById<ImageButton>(R.id.more_options)
            moreMenuOptions(iconMenu)
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

    private fun viewModelInstances(){
        viewModel.errorDescription.observe(this, Observer { description ->
            Toast.makeText(this, description, Toast.LENGTH_SHORT).show()
        })

        viewModel.successfully.observe(this, Observer { success ->
            if(success){
                binding.inputTask.text.clear()
            }
        })

    }

    private fun openAddTaskLayout(){
        binding.insertUpdateTaskLayout.visibility = View.VISIBLE
        binding.addTask.visibility = View.GONE
        binding.closeTaskLayout.setOnClickListener {
            closeAddTaskLayout()
        }

        binding.selectCategory.setOnClickListener { categoryMenu() }

        binding.openCalendar.setOnClickListener { openCalendar() }
    }

    private fun closeAddTaskLayout(){
        binding.insertUpdateTaskLayout.visibility = View.GONE
        binding.addTask.visibility = View.VISIBLE
    }

    private fun categoryMenu(){
        val openCategories: CardView = findViewById(R.id.select_category)
        val popupMenu = PopupMenu(this, openCategories)
        popupMenu.menuInflater.inflate(R.menu.category_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener {
            viewModel.category.value = it.title.toString()
            true
        }
        popupMenu.show()
    }

    private fun openCalendar(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                viewModel.date.value = ( dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year )
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun themeMode(){
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> {
                lightTheme()
            }
        }
    }

    private fun lightTheme(){
        binding.inputTask.setBackgroundResource(R.drawable.background_input_light)
        binding.openCalendar.background.setTint(Color.parseColor("#DBDEE2"))
        binding.selectCategory.background.setTint(Color.parseColor("#DBDEE2"))
        binding.saveTask.background.setTint(Color.parseColor("#DBDEE2"))
    }

    private fun moreMenuOptions(menu: ImageButton){
        val popup = PopupMenu(this, menu)
        popup.inflate(R.menu.menu_more_options)
        popup.setOnMenuItemClickListener {
            if(it.title!!.contains("Tareas de hoy")){
                startActivity(Intent(this, TodayTask::class.java))
            }
            if(it.title!!.contains("Historial")){
                startActivity(Intent(this, TaskHistoryActivity::class.java))
            }
            true
        }
        popup.show()
    }

}