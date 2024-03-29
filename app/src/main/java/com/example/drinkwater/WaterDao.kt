package com.example.drinkwater

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WaterDao {
    @Query("SELECT * FROM water_table")
    fun getAll(): Flow<List<WaterEntity>>

    //Insert new item to the Water table
    @Insert
    fun insert(waters: WaterEntity)

    // Delete all date from Water table
    @Query("DELETE FROM water_table")
    fun DeleteAll()

    // Delete single item form table
    @Delete
    fun deleteItem(water: WaterEntity)
}