package com.example.to_dolist.ui.common

import android.content.Context
import android.widget.PopupMenu
import androidx.cardview.widget.CardView
import com.example.to_dolist.R
import com.example.to_dolist.data.room.Task
import com.example.to_dolist.ui.common.Share.Companion.shareTask
import com.example.to_dolist.viewModel.TaskViewModel

class PopUp {
    companion object{
         fun popUpDisplay(context: Context, task: Task, cardView: CardView, viewModel: TaskViewModel){
            val popup = PopupMenu(context, cardView)
            popup.inflate(R.menu.delete_menu)
            popup.setOnMenuItemClickListener {
                if(it.title!!.contains("Borrar")){
                    viewModel.deleteTask(task)
                }
                if(it.title!!.contains("Compartir")){
                    shareTask(task, context)
                }
                true
            }
            popup.show()
        }
    }
}