package com.example.to_dolist.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "task") val task: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "category") val category: String,
)