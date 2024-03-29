package com.example.drinkwater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val water = mutableListOf<WaterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up Recycle View
        val rvWater: RecyclerView = findViewById(R.id.rvWater)
        val waterAdapter = WaterAdapter(this, water)

        lifecycleScope.launch {
            (application as WaterApplication).db.waterDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    WaterEntity(entity.amount, entity.time)
                }.also { mappedList ->
                    water.clear()
                    water.addAll(mappedList)
                    waterAdapter.notifyDataSetChanged()
                }
            }
        }

        rvWater.adapter = waterAdapter
        rvWater.layoutManager = LinearLayoutManager(this)
            .also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            rvWater.addItemDecoration(dividerItemDecoration)
        }

        // Set button click
        findViewById<Button>(R.id.btnAddMore).setOnClickListener {
            val intent = Intent(this, AddWater::class.java)
            startActivity(intent)
        }

    }
}