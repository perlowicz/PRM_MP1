package com.example.scheduler.model

import com.example.scheduler.R

object DataSource {
    val dishes = mutableListOf(
        Dish(
            "pizza",
            listOf("pierwszy", "drugi"),
            R.drawable.pizza
        ),
        Dish(
            "pierogi",
            listOf("trzeci", "czwarty"),
            R.drawable.pierogi
        )
    )
}