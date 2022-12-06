package com.example.to_dolist.repository

import androidx.lifecycle.LiveData
import com.example.to_dolist.data.room.Task
import com.example.to_dolist.data.room.TaskDao
import javax.inject.Inject

class TaskRepository
    @Inject
    constructor(
        private val taskDao: TaskDao
    ){

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task){
        taskDao.addTask(task)
    }

    fun tasksByCategory(category: String): LiveData<List<Task>> = taskDao.getTasksByCategory(category)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)


}