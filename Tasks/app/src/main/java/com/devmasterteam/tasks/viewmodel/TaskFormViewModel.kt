package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository()
    private val priorityRepository = PriorityRepository(application.applicationContext)
    var priorites = priorityRepository.list().asLiveData()

    private val _saveResult = MutableLiveData<Boolean>()
    val saveResult: LiveData<Boolean> = _saveResult

    fun save(task: TaskModel){
        viewModelScope.launch {
            val response = taskRepository.save(task)
            _saveResult.value = response.isSuccessful
        }
    }
}