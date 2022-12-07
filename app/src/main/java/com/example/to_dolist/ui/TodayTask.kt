package com.example.to_dolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.to_dolist.databinding.ActivityTodayTaskBinding
import com.example.to_dolist.ui.common.RecyclerViewIns
import com.example.to_dolist.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TodayTask : AppCompatActivity() {

    private lateinit var binding: ActivityTodayTaskBinding

    private val viewModel: TaskViewModel by viewModels()

    private lateinit var recyclerViewIns: RecyclerViewIns

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodayTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelInstances()

        binding.backLayout.setOnClickListener {
            onBackPressed()
        }
    }

    private fun viewModelInstances(){
        viewModel.todayTask(getCurrentDate()).observe(this) { task ->
            recyclerViewIns = RecyclerViewIns(
                binding.recyclerTodayTask,
                task,
                this,
                viewModel
            )
            recyclerViewIns.initRecyclerView()
        }

    }

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("d/M/yyyy")
        return sdf.format(Date())
    }
}