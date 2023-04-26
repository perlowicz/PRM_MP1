package com.example.scheduler.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.scheduler.Navigable
import com.example.scheduler.adapters.DishesAdapter
import com.example.scheduler.databinding.FragmentListBinding
import com.example.scheduler.model.DataSource


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
        adapter = DishesAdapter().apply {
            replace(DataSource.dishes)
        }

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

//        binding.list.addOnItemTouchListener()
    }

    override fun onStart() {
        super.onStart()
        adapter?.replace(DataSource.dishes)
    }
}