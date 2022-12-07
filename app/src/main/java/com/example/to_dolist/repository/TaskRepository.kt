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
    fun tasksByCategory(category: String): LiveData<List<Task>> = taskDao.getTasksByCategory(category)
    fun todayTask(date: String): LiveData<List<Task>> = taskDao.getTodayTasks(date)

    suspend fun insert(task: Task){
        taskDao.addTask(task)
    }

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)


}