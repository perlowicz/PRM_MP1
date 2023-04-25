package com.example.scheduler.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scheduler.adapters.DishImagesAdapter
import com.example.scheduler.Navigable
import com.example.scheduler.data.DishDatabase
import com.example.scheduler.data.model.DishEntity
import com.example.scheduler.databinding.FragmentEditBinding
import kotlin.concurrent.thread

class EditFragment : Fragment() {

    private lateinit var binding: FragmentEditBinding
    private lateinit var adapter: DishImagesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEditBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DishImagesAdapter()

        binding.images.apply {
            adapter = this@EditFragment.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        binding.save.setOnClickListener {

            val newDish = DishEntity(
                name = binding.dishName.text.toString(),
                ingredients = binding.ingredients.text.toString(),
                icon = resources.getResourceEntryName(adapter.selectedIdRes)
            )

            thread {
                DishDatabase.open(requireContext()).dishes.addDish(newDish)
                (activity as? Navigable)?.navigate(Navigable.Destination.List)
            }
        }
    }


}