package com.example.to_dolist.ui.common

import android.content.Context
import android.content.Intent
import com.example.to_dolist.data.room.Task

class Share {
    companion object{
        fun shareTask(task: Task, context: Context){
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            val message = "\uD83D\uDCCB Tarea: ${task.task} \n\uD83D\uDDD3 Fecha: ${task.date} \n\uD83D\uDDC2 Categoria: ${task.category}"
            share.putExtra(Intent.EXTRA_TEXT, message)
            context.startActivity(Intent.createChooser(share, "Compartir"))
        }
    }
}