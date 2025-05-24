package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository()

    private val _tasksResult = MutableLiveData<List<TaskModel>>()
    val tasksResult = _tasksResult

    fun getAll(){
        viewModelScope.launch {
            val response = taskRepository.getAll()

            if ( response.isSuccessful && response.body() != null){
                _tasksResult.value = response.body()
            }
        }
    }
}