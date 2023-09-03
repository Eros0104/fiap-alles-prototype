package com.example.myapplication

import android.content.Context
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Transaction
import com.example.myapplication.models.TransactionType

import java.time.format.DateTimeFormatter

class TransactionAdapter(private val transactions: MutableList<Transaction>, private val context: Context): RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent,false)
        return TransactionViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        return holder.bindView(transactions[position])
    }

}

class TransactionViewHolder(itemView: View, val context: Context): RecyclerView.ViewHolder(itemView) {
    private val tvDescription: TextView = itemView.findViewById(R.id.tv_movement_description)
    private val tvValue: TextView = itemView.findViewById(R.id.tv_movement_value)
    private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
    fun bindView(transaction: Transaction) {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val nf: NumberFormat = NumberFormat.getCurrencyInstance()
        nf.maximumFractionDigits = 2
        nf.currency = Currency.getInstance("BRL");

        val formattedDate = transaction.date.format(dateFormatter)
        val formattedValue = nf.format(transaction.value)
        val mathSign: String
        val color: Int

        if (transaction.transactionType == TransactionType.DEBIT) {
            mathSign = "-"
            color = context.getColor(R.color.red)
        } else {
            mathSign = "+"
            color = context.getColor(R.color.green)
        }

        tvDescription.text = transaction.description
        tvDate.text = formattedDate
        tvValue.text = mathSign + formattedValue
        tvValue.setTextColor(color)
    }
}