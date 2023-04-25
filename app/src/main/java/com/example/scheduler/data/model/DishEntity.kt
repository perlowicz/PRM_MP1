package com.example.scheduler.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class DishEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val ingredients: String,
    val icon: String
)
