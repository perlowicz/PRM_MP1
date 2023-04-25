package com.example.scheduler.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scheduler.DishesAdapter
import com.example.scheduler.Navigable
import com.example.scheduler.data.DishDatabase
import com.example.scheduler.databinding.FragmentListBinding
import com.example.scheduler.model.Dish
import kotlin.concurrent.thread


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private var adapter: DishesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DishesAdapter()
        loadData()

        binding.list.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.btAdd.setOnClickListener {
            (activity as? Navigable)?.navigate(Navigable.Destination.Add)
        }

        binding.btSort.setOnClickListener {
            adapter?.sort()
        }
    }

    fun loadData() =  thread {
        val dishes = DishDatabase.open(requireContext()).dishes.getAll().map { entity ->
            Dish(
                entity.name,
                entity.ingredients.split("\n"),
                resources.getIdentifier(entity.icon, "drawable", requireContext().packageName)
            )
        }

//        requireActivity().runOnUiThread {
        adapter?.replace(dishes)
//        }
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

}