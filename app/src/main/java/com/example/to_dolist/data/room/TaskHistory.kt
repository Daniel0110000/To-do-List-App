package com.example.to_dolist.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_history_table")
data class TaskHistory(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name ="task_history") val taskHistory: String,
    @ColumnInfo(name = "date_history") val dateHistory: String,
    @ColumnInfo(name = "category_history") val category_history: String

)