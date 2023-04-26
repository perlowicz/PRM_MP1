package com.example.scheduler.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scheduler.R
import com.example.scheduler.databinding.DishImageBinding

class DishImageViewHolder(val binding: DishImageBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun bind(resId: Int, isSelected: Boolean) {
        binding.image.setImageResource(resId)
        binding.selectedFrame.visibility =
            if (isSelected) View.VISIBLE else View.INVISIBLE
    }
}

class DishImagesAdapter : RecyclerView.Adapter<DishImageViewHolder>() {

    private val images = listOf(
        R.drawable.pierogi,
        R.drawable.pizza,
        R.drawable.pumpkin,
        R.drawable.rice,
        R.drawable.rosol,
    )
    private var selectedPosition: Int = 0
    val selectedIdRes: Int
    get() = images[selectedPosition]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishImageViewHolder {
        val binding = DishImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DishImageViewHolder(binding).also { vh ->
            binding.root.setOnClickListener {
                notifyItemChanged(selectedPosition)
                selectedPosition = vh.layoutPosition
                notifyItemChanged(selectedPosition)
            }
        }
    }

    override fun onBindViewHolder(holder: DishImageViewHolder, position: Int) {
        holder.bind(images[position], position == selectedPosition)
    }

    override fun getItemCount(): Int = images.size
}