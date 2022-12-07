package com.example.to_dolist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.to_dolist.R
import com.example.to_dolist.data.room.Task
import kotlin.random.Random

class RecyclerTaskAdapter(private val taskList: List<Task>, val listener: TaskClickListener): RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_rows_design, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.print(position)
    }

    override fun getItemCount(): Int = taskList.size

    fun randomColor(): Int{
        val list = ArrayList<Int>()
        list.add(R.color.cheese)
        list.add(R.color.curious_blue)
        list.add(R.color.jade_green)
        list.add(R.color.dark_lavender)
        list.add(R.color.carmine_pink)
        val seed = System.currentTimeMillis().toInt()
        val randomIndex = Random(seed).nextInt(list.size)
        return list[randomIndex]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val taskLayout: CardView = itemView.findViewById(R.id.task_layout)
        private val taskDescription: TextView = itemView.findViewById(R.id.task_description)
        private val leftDesign: CardView = itemView.findViewById(R.id.left_design)
        private val taskCompleted: ImageView = itemView.findViewById(R.id.task_completed)

        fun print(position: Int){
            taskDescription.text = taskList[position].task
            leftDesign.setCardBackgroundColor(itemView.resources.getColor(randomColor(), null))

            taskLayout.setOnLongClickListener {
                listener.onLongItemClicked(taskList[adapterPosition], taskLayout)
                true
            }

            taskCompleted.setOnClickListener {
                taskCompleted.setImageResource(R.drawable.ic_check_box)
                listener.onCompletedItemClicked(taskList[adapterPosition])
            }

            val animation = AnimationUtils.loadAnimation(itemView.context, android.R.anim.slide_in_left)
            itemView.animation = animation


        }

    }

}