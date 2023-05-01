package com.example.myapplication

import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Movement

class MovementAdapter(val movements: MutableList<Movement>): RecyclerView.Adapter<MovementViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movement_item, parent,false)
        return MovementViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movements.size
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        return holder.bindView(movements[position])
    }

}

class MovementViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val tvDescription: TextView = itemView.findViewById(R.id.tv_movement_description)
    private val tvValue: TextView = itemView.findViewById(R.id.tv_movement_value)
    fun bindView(movement: Movement) {
        val nf: NumberFormat = NumberFormat.getCurrencyInstance()
        nf.maximumFractionDigits = 2
        nf.currency = Currency.getInstance("BRL");

        tvDescription.text = movement.description
        tvValue.text = nf.format(movement.value)
    }
}