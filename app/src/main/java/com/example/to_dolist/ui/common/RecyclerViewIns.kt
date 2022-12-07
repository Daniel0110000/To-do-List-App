package com.example.to_dolist.ui.common

import android.content.Context
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.adapter.RecyclerTaskAdapter
import com.example.to_dolist.adapter.TaskClickListener
import com.example.to_dolist.data.room.Task
import com.example.to_dolist.data.room.TaskHistory
import com.example.to_dolist.ui.common.PopUp.Companion.popUpDisplay
import com.example.to_dolist.viewModel.TaskViewModel

class RecyclerViewIns
    constructor(
        private val recycler: RecyclerView,
        private val taskList: List<Task>,
        private val context: Context,
        private val viewModel: TaskViewModel
    ): TaskClickListener {

    fun initRecyclerView(){
            recycler.hasFixedSize()
            recycler.layoutManager = LinearLayoutManager(context)
            recycler.adapter = RecyclerTaskAdapter(taskList, this)
    }

    override fun onLongItemClicked(task: Task, cardView: CardView) {
        popUpDisplay(context, task, cardView, viewModel)
    }

    override fun onCompletedItemClicked(task: Task) {
        viewModel.deleteTask(task)
        viewModel.addTaskHistory(TaskHistory(
            0,
            task.task,
            task.date,
            task.category
        ))

        Toast.makeText(context, "\uD83C\uDF89 ¡Tarea completada! \uD83C\uDF89", Toast.LENGTH_SHORT).show()

    }
}