package com.example.drinkwater

import android.app.Application

class WaterApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}