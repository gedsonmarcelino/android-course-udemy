package com.devmasterteam.tasks.service.repository

import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Response

class TaskRepository {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    suspend fun getAll(): Response<List<TaskModel>> {
        return remote.getAll()
    }

    suspend fun getOnNextDays(): Response<List<TaskModel>> {
        return remote.getOnNextDays()
    }

    suspend fun getOverdue(): Response<List<TaskModel>> {
        return remote.getOverdue()
    }

    suspend fun save(task: TaskModel): Response<Boolean> {
        return if (task.id > 0) {
            remote.update(
                task.id,
                task.priorityId,
                task.description,
                task.dueDate,
                task.complete
            )
        } else {
            remote.create(task.priorityId, task.description, task.dueDate, task.complete)
        }
    }

    suspend fun status(id: Int, value: Boolean): Response<Boolean> {
        if (value) {
            return remote.complete(id)
        }
        return remote.undo(id)
    }
}