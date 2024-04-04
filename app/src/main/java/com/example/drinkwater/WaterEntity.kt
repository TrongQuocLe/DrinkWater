package com.example.drinkwater

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_table")
class WaterEntity(
    @ColumnInfo(name = "Amount") val amount: Double,
    @ColumnInfo(name = "Time") val time: String?,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)