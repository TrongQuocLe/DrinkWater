package com.example.drinkwater

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class AddWater : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_water)

        findViewById<Button>(R.id.btnSave).setOnClickListener {
            val etAmount = findViewById<EditText>(R.id.etAmount).text.toString()

            if (etAmount.isNotEmpty()) {
                val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm a")

                // Add new water to the database
                lifecycleScope.launch(Dispatchers.IO) {
                    (application as WaterApplication).db.waterDao().insert(
                        WaterEntity(
                            amount = etAmount.toDouble(),
                            time = dateFormat.format(Date())
                        )
                    )
                    Log.d("AddWater", dateFormat.format(Date()))

                }

                // return to previous screen
                finish()
            }
            else {
                Toast.makeText(this, "Please enter some data", Toast.LENGTH_LONG).show()
            }
        }

    }
}