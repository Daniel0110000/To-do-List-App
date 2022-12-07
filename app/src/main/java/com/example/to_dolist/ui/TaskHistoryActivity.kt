package com.example.to_dolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.to_dolist.adapter.RecyclerTaskHistoryAdapter
import com.example.to_dolist.data.room.TaskHistory
import com.example.to_dolist.databinding.ActivityTaskHistoryBinding
import com.example.to_dolist.viewModel.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskHistoryBinding

    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModelInstances()

        binding.backLayout.setOnClickListener {
            onBackPressed()
        }

        binding.deleteAll.setOnClickListener {
            viewModel.deleteAllTaskHistory()
        }

    }

    private fun viewModelInstances(){
        viewModel.allTasksHistory.observe(this) { task ->
            initRecyclerView(task)
        }
    }

    private fun initRecyclerView(taskHistory: List<TaskHistory>){
        binding.recyclerTaskHistory.hasFixedSize()
        binding.recyclerTaskHistory.layoutManager = LinearLayoutManager(this)
        binding.recyclerTaskHistory.adapter = RecyclerTaskHistoryAdapter(taskHistory)
    }
}