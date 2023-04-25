package com.example.scheduler

interface Navigable {
    enum class Destination {
        List, Add
    }
    fun navigate(to: Destination)
}