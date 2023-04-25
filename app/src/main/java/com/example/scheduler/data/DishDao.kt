package com.example.scheduler.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.scheduler.data.model.DishEntity

@Dao
interface DishDao {
    @Query("SELECT * FROM dish;")
    fun getAll(): List<DishEntity>

    @Query("SELECT * FROM dish ORDER BY name ASC;")
    fun getAllSortedByName(): List<DishEntity>

    @Insert
    fun addDish(newDish: DishEntity)

    @Update
    fun updateDish(newDish: DishEntity)
}