package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.model.ValidationModel
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.TaskRepository
import com.devmasterteam.tasks.viewmodel.helpers.ResponseHelper
import kotlinx.coroutines.launch

class TaskFormViewModel(application: Application) : BaseViewModel(application) {

    private val taskRepository = TaskRepository(application.applicationContext)
    private val priorityRepository = PriorityRepository(application.applicationContext)
    var priorites = priorityRepository.list().asLiveData()

    private val _saveResult = MutableLiveData<ValidationModel>()
    val saveResult: LiveData<ValidationModel> = _saveResult

    private val _task = MutableLiveData<TaskModel>()
    val task: LiveData<TaskModel> = _task

    fun save(task: TaskModel) {
        viewModelScope.launch {
            try {
                val response = taskRepository.save(task)
                _saveResult.value = ResponseHelper.getResult(response)
            } catch (error : Exception){
                _saveResult.value = handleException(error)
            }
        }
    }

    fun load(id:Int){
        viewModelScope.launch {
            val response = taskRepository.load(id)
            if (ResponseHelper.isSuccessfull(response) ){
                _task.value = response.body()
            }
        }
    }
}