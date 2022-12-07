package com.example.to_dolist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
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
            Animatoo.animateSlideRight(this)
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
        if(taskHistory.isEmpty()){
            binding.noHistoryOfCompletedTasksLayout.visibility = View.VISIBLE
            binding.recyclerTaskHistory.visibility = View.GONE
        }else{
            binding.noHistoryOfCompletedTasksLayout.visibility = View.GONE
            binding.recyclerTaskHistory.visibility = View.VISIBLE
            binding.recyclerTaskHistory.hasFixedSize()
            binding.recyclerTaskHistory.layoutManager = LinearLayoutManager(this)
            binding.recyclerTaskHistory.adapter = RecyclerTaskHistoryAdapter(taskHistory)
        }
    }
}