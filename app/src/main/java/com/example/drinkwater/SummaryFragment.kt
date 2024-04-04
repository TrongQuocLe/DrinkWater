package com.example.drinkwater

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class SummaryFragment(private val waters: List<WaterEntity>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        var entryCount = waters.size
        view.findViewById<TextView>(R.id.tvEntryCount).text = "Number entry: $entryCount"
        Log.d("Summary", "$entryCount")

        var totalAmountWater = 0.0
        waters.forEach{ water ->
            totalAmountWater += water.amount
        }
        view.findViewById<TextView>(R.id.tvTotalAmountWater).text = "Total Amount of Water Drunk: $totalAmountWater L"
        return view
    }
}