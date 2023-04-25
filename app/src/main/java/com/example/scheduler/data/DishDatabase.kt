package com.example.scheduler.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.scheduler.data.model.DishEntity

@Database(
    entities = [DishEntity::class],
    version = 1
)
abstract class DishDatabase : RoomDatabase() {
    abstract val dishes: DishDao

    companion object {
        fun open(context: Context): DishDatabase = Room.databaseBuilder(
            context, DishDatabase::class.java, "dishes.db"
        ).build()
    }
}