package com.hfad.engineeringcalculator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ECDAO {
    @Insert
    suspend fun insert(expression: ECEntity)

    @Insert
    suspend fun insertAll(list: List<ECEntity>)

    @Update
    suspend fun update(expression: ECEntity)

    @Update
    suspend fun updateAll(list: List<ECEntity>)

    @Delete
    suspend fun delete(expression: ECEntity)

    @Delete
    suspend fun deleteAll(list: List<ECEntity>)

    @Query("SELECT * FROM ECTable WHERE id = :id")
    fun get(id: Long): LiveData<ECEntity>

    @Query("SELECT * FROM ECTable ORDER BY id DESC")
    fun getAll(): LiveData<List<ECEntity>>
}