package com.example.to_dolist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_dolist.data.room.Task
import com.example.to_dolist.data.room.TaskHistory
import com.example.to_dolist.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskViewModel
    @Inject
    constructor(
        private val taskRepository: TaskRepository
    ): ViewModel() {

    val bodyTask = MutableLiveData<String>()
    val date = MutableLiveData<String>()
    val category = MutableLiveData<String>()
    val errorDescription = MutableLiveData<String>()
    val successfully = MutableLiveData<Boolean>()
    val allTasks: LiveData<List<Task>>
    val allTasksHistory: LiveData<List<TaskHistory>>

    init {
        allTasks = taskRepository.allTasks
        allTasksHistory= taskRepository.allTasksHistory
        bodyTask.value = ""
        date.value = ""
        category.value = ""
    }


    fun addTask(){
        val bodyTaskString = bodyTask.value.toString()
        val dateString = date.value.toString()
        val categoryString = category.value.toString()

        if(bodyTaskString.isNotEmpty()){
            if(dateString.isNotEmpty()){
                if(categoryString.isNotEmpty()){
                    viewModelScope.launch(Dispatchers.IO) {
                        taskRepository.insert(Task(
                            0,
                            bodyTask.value.toString(),
                            date.value.toString(),
                            category.value.toString()
                        ))
                        withContext(Dispatchers.Main){
                            bodyTask.value = ""
                            date.value = ""
                            category.value = ""
                            successfully.value = true
                        }
                    }
                }else{
                    errorDescription.value = "\uD83D\uDCCB Por favor selecciona una categoría."
                }
            }else{
                errorDescription.value = "\uD83D\uDD2E Por favor selecciona una fecha."
            }
        }else{
            errorDescription.value = "\uD83D\uDCD6 Por favor escriba una tarea."
        }
    }

    fun addTaskHistory(taskHistory: TaskHistory) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.insertTaskHistory(taskHistory)
    }

    fun tasksByCategory(category: String): LiveData<List<Task>> = taskRepository.tasksByCategory(category)
    fun todayTask(date: String): LiveData<List<Task>> = taskRepository.todayTask(date)

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.deleteTask(task)
    }

    fun deleteAllTaskHistory() = viewModelScope.launch(Dispatchers.IO) {
        taskRepository.deleteAllTasksHistory()
    }

}