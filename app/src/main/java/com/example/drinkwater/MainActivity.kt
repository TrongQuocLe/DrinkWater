package com.example.drinkwater

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val water = mutableListOf<WaterEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set button click
        findViewById<Button>(R.id.btnAddMore).setOnClickListener {
            val intent = Intent(this, AddWater::class.java)
            startActivity(intent)
        }

        val listFragment: Fragment = ListFragment()
        val summaryFragment: Fragment = SummaryFragment()
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


        }
        private fun replaceFragment(fragment: Fragment) {
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frameLayout,fragment)
            fragmentTransaction.commit()
    }
}