package com.example.drinkwater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WaterAdapter (
    private val context: Context,
    private val water: MutableList<WaterEntity>) : RecyclerView.Adapter<WaterAdapter.WaterViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    inner class WaterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTime: TextView = itemView.findViewById(R.id.tvTime)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WaterAdapter.WaterViewHolder {
        val itemView = inflater.inflate(R.layout.water_item, parent, false)
        return WaterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WaterViewHolder, position: Int) {
        val current = water[position]
        holder.tvTime.text = current.time
        holder.tvAmount.text = current.amount.toString()
    }

//    internal fun setWater(water: List<WaterEntity>) {
//        this.water = water
//        notifyDataSetChanged()
//    }

//    fun getWordAtPosition(position: Int): WaterEntity {
//        return water[position]
//    }

    override fun getItemCount() = water.size
}