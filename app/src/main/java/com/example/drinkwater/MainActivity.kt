package com.example.drinkwater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val water = mutableListOf<WaterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchData()

        // define fragments
        val listFragment: Fragment = ListFragment()
        val summaryFragment: Fragment = SummaryFragment(water)

        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_listTab -> fragment = listFragment
                R.id.nav_summaryTab -> fragment = summaryFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigation.selectedItemId = R.id.nav_listTab

        // Set add more button click
        findViewById<Button>(R.id.btnAddMore).setOnClickListener {
            val intent = Intent(this, AddWater::class.java)
            startActivity(intent)
        }





        // Notification
//        val CHANNEL_ID = "Drink"
//        var notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setContentTitle("Drink Water")
//            .setContentText("Time to drink water")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
    }

    fun fetchData() {
        lifecycleScope.launch {
            (application as WaterApplication).db.waterDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    WaterEntity(entity.amount, entity.time)
                }.also { mappedList ->
                    water.clear()
                    water.addAll(mappedList)
                }
            }
        }
    }

}