package com.example.drinkwater

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private val water = mutableListOf<WaterEntity>()
    private lateinit var waterAdapter: WaterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Set up Recycle View
        val rvWater: RecyclerView = view.findViewById(R.id.rvWater)
        waterAdapter = WaterAdapter(view.context, water)

//        lifecycleScope.launch {
//            (requireActivity().application as WaterApplication).db.waterDao().getAll().collect { databaseList ->
//                databaseList.map { entity ->
//                    WaterEntity(entity.amount, entity.time)
//                }.also { mappedList ->
//                    water.clear()
//                    water.addAll(mappedList)
//                    waterAdapter.notifyDataSetChanged()
//                }
//            }
//        }

        rvWater.adapter = waterAdapter
        rvWater.layoutManager = LinearLayoutManager(view.context)
            .also {
                val dividerItemDecoration = DividerItemDecoration(view.context, it.orientation)
                rvWater.addItemDecoration(dividerItemDecoration)
            }

        return view
    }

    private fun fetchData() {
        lifecycleScope.launch {
            (requireActivity().application as WaterApplication).db.waterDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    WaterEntity(entity.amount, entity.time)
                }.also { mappedList ->
                    water.clear()
                    water.addAll(mappedList)
                    waterAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
    }
    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}