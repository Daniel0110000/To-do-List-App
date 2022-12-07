package com.example.to_dolist.repository

import androidx.lifecycle.LiveData
import com.example.to_dolist.data.room.Task
import com.example.to_dolist.data.room.TaskDao
import com.example.to_dolist.data.room.TaskHistory
import com.example.to_dolist.data.room.TaskHistoryDao
import javax.inject.Inject

class TaskRepository
    @Inject
    constructor(
        private val taskDao: TaskDao,
        private val taskHistoryDao: TaskHistoryDao,
    ){

    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()
    val allTasksHistory: LiveData<List<TaskHistory>> = taskHistoryDao.getAllTasksHistory()
    fun tasksByCategory(category: String): LiveData<List<Task>> = taskDao.getTasksByCategory(category)
    fun todayTask(date: String): LiveData<List<Task>> = taskDao.getTodayTasks(date)

    suspend fun insert(task: Task) = taskDao.addTask(task)
    suspend fun insertTaskHistory(taskHistory: TaskHistory) = taskHistoryDao.addTaskHistory(taskHistory)

    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun deleteAllTasksHistory() = taskHistoryDao.deleteAllTasksHistory()


}