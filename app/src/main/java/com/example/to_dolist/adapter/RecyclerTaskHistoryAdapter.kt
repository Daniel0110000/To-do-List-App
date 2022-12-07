package com.example.to_dolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.R
import com.example.to_dolist.data.room.TaskHistory

class RecyclerTaskHistoryAdapter(private val taskHistoryList: List<TaskHistory>): RecyclerView.Adapter<RecyclerTaskHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_history_rows_design, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.print(position)
    }

    override fun getItemCount(): Int = taskHistoryList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val dateHistory: TextView = itemView.findViewById(R.id.date_history)
        private val taskHistory: TextView = itemView.findViewById(R.id.task_history)

        fun print(position: Int){
            dateHistory.text = taskHistoryList[position].dateHistory
            taskHistory.text = taskHistoryList[position].taskHistory
        }
    }

}