package com.devmasterteam.tasks.service.repository

import android.content.Context
import com.devmasterteam.tasks.service.model.PriorityModel
import com.devmasterteam.tasks.service.repository.local.TaskDatabase
import com.devmasterteam.tasks.service.repository.remote.PriorityService
import com.devmasterteam.tasks.service.repository.remote.RetrofitClient
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class PriorityRepository(context: Context) {

    private val remote = RetrofitClient.getService(PriorityService::class.java)
    private val database = TaskDatabase.getDatabase(context).priorityDAO()

    companion object {
        private var cache = mutableMapOf<Int, String>()

        fun setCache(id: Int, value: String) {
            cache[id] = value
        }

        fun getCache(id: Int) =  cache[id] ?: ""
    }

    suspend fun getAll(): Response<List<PriorityModel>> {
        return remote.getAll()
    }

    suspend fun save(list: List<PriorityModel>) {
        database.clear()
        database.save(list)
    }

    fun list(): Flow<List<PriorityModel>> {
        return database.list()
    }

    suspend fun getDescription(id: Int): String {
        var valueFromCache = getCache(id)
        if ( valueFromCache == "" ){
            valueFromCache = database.getDescription(id)
            setCache(id, valueFromCache)
        }
        return valueFromCache
    }
}