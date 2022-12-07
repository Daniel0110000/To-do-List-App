package com.example.to_dolist.adapter

import androidx.cardview.widget.CardView
import com.example.to_dolist.data.room.Task

interface TaskClickListener {
    fun onLongItemClicked(task: Task, cardView: CardView)
    fun onCompletedItemClicked(task: Task)
}