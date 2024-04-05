package com.example.drinkwater

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch

class SummaryFragment(private val waters: List<WaterEntity>) : Fragment() {

    // variable for our bar chart
    lateinit var barChart: BarChart

    // variable for our bar data.
    lateinit var barDate: BarData

    // variable for our bar data set.
    lateinit var barDateSet: BarDataSet

    // array list for storing entries.
    lateinit var barEntriesArrayList: ArrayList<BarEntry>
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

        // Chart bar
        // initializing variable for bar chart.
        val barChart = view.findViewById<BarChart>(R.id.idBarChart)

        // calling method to get bar entries.
        getBarEntries()

        // creating a new bar data set.
        val barDataSet = BarDataSet(barEntriesArrayList, "Liter of Water")

        // creating a new bar data and passing our bar data set.
        val barData = BarData(barDataSet)

        // below line is to set data to our bar chart.
        barChart.data = barData

        // adding color to our bar data set.
        barDataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()

        // setting text color.
        barDataSet.valueTextColor = Color.BLACK

        // setting text size
        barDataSet.valueTextSize = 16f

        // setting description as disabled
        barChart.description.isEnabled = false

        return view
    }

    private fun getBarEntries() {
        // creating a new array list
        barEntriesArrayList = ArrayList()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        waters.forEachIndexed{ index, water ->
            barEntriesArrayList.add(BarEntry(index.toFloat(),water.amount.toFloat()))
        }
    }

}