package com.devmasterteam.tasks.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.devmasterteam.tasks.service.model.PriorityModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PriorityDAO {

    @Insert
    suspend fun save(list: List<PriorityModel>)

    @Query("SELECT * FROM PRIORITY_DB")
    fun list(): Flow<List<PriorityModel>>

    @Query("SELECT description FROM PRIORITY_DB WHERE id = :id")
    suspend fun getDescription(id: Int): String

    @Query("DELETE FROM PRIORITY_DB")
    suspend fun clear()
}