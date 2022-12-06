package com.example.to_dolist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskDao {

    @Query("select * from task_table")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("select * from task_table where category = :category")
    fun getTasksByCategory(category: String): LiveData<List<Task>>

    @Delete
    suspend fun deleteTask(task: Task)

}