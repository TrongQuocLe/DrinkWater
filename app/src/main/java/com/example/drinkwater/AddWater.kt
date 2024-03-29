package com.example.drinkwater

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddWater : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_water)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val etAmount = findViewById<EditText>(R.id.etAmount).text.toString()

            // Add new water to the database
            lifecycleScope.launch {
                (application as WaterApplication).db.waterDao().insert(
                    WaterEntity(
                        amount = etAmount,
                        time = "now"
                    )
                )
            }

            // return to previous screen
            finish()
        }

    }
}