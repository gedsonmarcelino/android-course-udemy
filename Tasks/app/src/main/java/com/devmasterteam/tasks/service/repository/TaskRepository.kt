package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.model.TaskModel
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import com.devmasterteam.tasks.service.repository.remote.TaskService
import retrofit2.Response

class TaskRepository(context: Context) : BaseRespository(context) {

    private val remote = RetrofitClient.getService(TaskService::class.java)

    suspend fun getAll(): Response<List<TaskModel>> {
        return safeApiCall {
            remote.getAll()
        }
    }

    suspend fun getOnNextDays(): Response<List<TaskModel>> {
        return safeApiCall {
            remote.getOnNextDays()
        }
    }

    suspend fun getOverdue(): Response<List<TaskModel>> {
        return safeApiCall {
            remote.getOverdue()
        }
    }

    suspend fun save(task: TaskModel): Response<Boolean> {
        return safeApiCall {
            if (task.id > 0) {
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
    }

    suspend fun status(id: Int, value: Boolean): Response<Boolean> {
        return safeApiCall {
            if (value) {
                remote.complete(id)
            } else {
                remote.undo(id)
            }
        }
    }

    suspend fun delete(id: Int): Response<Boolean> {
        return safeApiCall {
            remote.delete(id)
        }
    }

    suspend fun load(id: Int): Response<TaskModel> {
        return safeApiCall {
            remote.getById(id)
        }
    }
}