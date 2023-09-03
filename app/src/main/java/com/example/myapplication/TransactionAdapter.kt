package com.example.myapplication

import android.content.Context
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.models.Category
import com.example.myapplication.models.Transaction
import com.example.myapplication.models.TransactionType

import java.time.format.DateTimeFormatter

data class GetTransactionTypeConfiguration(
    val mathSign: String,
    val color: Int
);
class TransactionAdapter(
    private val transactions: MutableList<Transaction>,
    private val context: Context
) : RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        return TransactionViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        return holder.bindView(transactions[position])
    }

}

class TransactionViewHolder(itemView: View, val context: Context) :
    RecyclerView.ViewHolder(itemView) {
    private val tvDescription: TextView = itemView.findViewById(R.id.tv_movement_description)
    private val tvValue: TextView = itemView.findViewById(R.id.tv_movement_value)
    private val tvDate: TextView = itemView.findViewById(R.id.tv_date)
    private val ivIcon: ImageView = itemView.findViewById(R.id.img_transaction_icon)

    private fun getIcon(category: Category): Int {
        return when (category) {
            Category.MARKET -> R.drawable.cart_icon
            Category.RESTAURANT -> R.drawable.restaurant_icon
            Category.TRANSPORT -> R.drawable.transport_icon
            else -> R.drawable.money_icon
        }
    }

    private fun getTransactionTypeConfiguration(transactionType: TransactionType): GetTransactionTypeConfiguration {
        var mathSign: String;
        var color: Int;

        if (transactionType == TransactionType.DEBIT) {
            mathSign = "-"
            color = context.getColor(R.color.red)
        } else {
            mathSign = "+"
            color = context.getColor(R.color.green)
        }

        return GetTransactionTypeConfiguration(mathSign, color)
    }

    private fun formatTransactionDate(transaction: Transaction): String {
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        return transaction.date.format(dateFormatter)
    }

    private fun formatTransactionValue(transaction: Transaction): String {
        val nf: NumberFormat = NumberFormat.getCurrencyInstance()
        nf.maximumFractionDigits = 2
        nf.currency = Currency.getInstance("BRL");
        return nf.format(transaction.value);
    }

    fun bindView(transaction: Transaction) {
        val transactionTypeConfig = getTransactionTypeConfiguration(transaction.transactionType)
        val icon = getIcon(transaction.category)
        val formattedDate = formatTransactionDate(transaction)
        val formattedValue = formatTransactionValue(transaction)

        tvDescription.text = transaction.description
        tvDate.text = formattedDate
        tvValue.text = transactionTypeConfig.mathSign + formattedValue
        tvValue.setTextColor(transactionTypeConfig.color)
        ivIcon.setImageResource(icon)
    }
}