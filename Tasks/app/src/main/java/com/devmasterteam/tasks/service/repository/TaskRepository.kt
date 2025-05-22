package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Response

class TaskRepository {

    private val remote = RetrofitClient.getService(TaskService::class.java)

//    suspend fun tasks(): Response<List<TaskModel>> {
//        return remote.tasks()
//    }
}