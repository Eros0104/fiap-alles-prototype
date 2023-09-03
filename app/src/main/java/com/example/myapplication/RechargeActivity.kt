package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.services.TransactionService

class RechargeActivity : AppCompatActivity() {
    private val transactionService = TransactionService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge)

        fetchTransactions()
        configGoBackButton()
        configRecharge()
    }

    private fun fetchTransactions() {
        val recyclerView = findViewById<RecyclerView>(R.id.transactionRecyclerView)
        val transactions = transactionService.getCreditTransactions()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RechargeActivity)
            adapter = TransactionAdapter(transactions, this@RechargeActivity)
        }
    }

    private fun configGoBackButton() {
        val btnGoBack = findViewById<Button>(R.id.btnGoBack)

        btnGoBack.setOnClickListener {
            finish()
        }
    }

    private fun configRecharge() {
        val rechargeBtn = findViewById<Button>(R.id.btnRecharge)

        rechargeBtn.setOnClickListener {
            val etValue = findViewById<EditText>(R.id.et_value).text.toString()
            val value = etValue.toDouble()

            transactionService.recharge(value, getString(R.string.you))
            fetchTransactions()
        }
    }
}