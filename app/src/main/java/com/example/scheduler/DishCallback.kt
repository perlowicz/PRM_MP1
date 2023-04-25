package com.example.scheduler

import androidx.recyclerview.widget.DiffUtil
import com.example.scheduler.model.Dish

class DishCallback(val notSorted: List<Dish>, val sorted: List<Dish>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = notSorted.size

    override fun getNewListSize(): Int = sorted.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] === sorted[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        notSorted[oldItemPosition] == sorted[newItemPosition]
}