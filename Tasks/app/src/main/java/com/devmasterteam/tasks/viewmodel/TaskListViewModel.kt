package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.devmasterteam.tasks.service.constants.TaskConstants
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.PriorityRepository
import com.devmasterteam.tasks.service.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskListViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository()
    private val priorityRepository = PriorityRepository(application.applicationContext)

    private val _tasksResult = MutableLiveData<List<TaskModel>>()
    val tasksResult = _tasksResult

    private var taskFilter = TaskConstants.FILTER.ALL

    fun getAll(filter: Int) {
        taskFilter = filter
        viewModelScope.launch {
            val response = when (filter) {
                TaskConstants.FILTER.ALL -> taskRepository.getAll()
                TaskConstants.FILTER.NEXT -> taskRepository.getOnNextDays()
                else -> taskRepository.getOverdue()
            }

            if (response.isSuccessful && response.body() != null) {
                val result = response.body()!!

                result.map { task ->
                    task.priorityDescription = priorityRepository.getDescription(task.priorityId)
                }

                _tasksResult.value = result
            }
        }
    }

    fun setStatus(id: Int, value: Boolean) {
        viewModelScope.launch {
            val response = taskRepository.status(id, value)

            if ( response.isSuccessful && response.body() != null ){
                getAll(taskFilter)
            }
        }
    }
}