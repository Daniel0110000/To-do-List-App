package com.example.to_dolist.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TaskHistoryDao {

    @Query("select * from task_history_table")
    fun getAllTasksHistory(): LiveData<List<TaskHistory>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTaskHistory(taskHistory: TaskHistory)

    @Query("delete from task_history_table")
    suspend fun deleteAllTasksHistory()
}