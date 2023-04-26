package com.example.scheduler.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.HandlerCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduler.DishCallback
import com.example.scheduler.Navigable
import com.example.scheduler.databinding.FragmentEditBinding
import com.example.scheduler.databinding.ListItemBinding
import com.example.scheduler.model.Dish

class DishViewHolder(val binding: ListItemBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(dish: Dish) {
        binding.name.text = dish.name
        binding.ingredients.text = dish.ingredients.joinToString(",\n")
        binding.image.setImageResource(dish.resId)
    }
}

class DishesAdapter : RecyclerView.Adapter<DishViewHolder>() {
    private val data = mutableListOf<Dish>()
    private val handler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishViewHolder {
        val binding = ListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishViewHolder(binding).also { vh ->
            binding.root.setOnClickListener {
                //TODO
//                Dish(
//                    binding.name.text as String,
//                    binding.ingredients.text.split("\n"),
//                    binding.image.
//                )
//                vh.bind()
                println(binding.name.text)
            }
        }
    }

    override fun onBindViewHolder(holder: DishViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun replace(newData: List<Dish>) {
        val callback = DishCallback(data, newData)
        data.clear()
        data.addAll(newData)
        val result = DiffUtil.calculateDiff(callback)
        handler.post {
            result.dispatchUpdatesTo(this)
        }
    }

    fun sort() {
        val notSorted = data.toList()
        data.sortBy { it.name.length }
        val callback = DishCallback(notSorted, data)
        val result = DiffUtil.calculateDiff(callback)
        handler.post {
            result.dispatchUpdatesTo(this)
        }
    }
}