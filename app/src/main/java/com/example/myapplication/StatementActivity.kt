package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.services.TransactionService

class StatementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        fetchTransactions()
        configGoBackButton()
    }

    private fun fetchTransactions() {
        val recyclerView = findViewById<RecyclerView>(R.id.transactionRecyclerView)
        val transactionService = TransactionService
        val transactions = transactionService.getTransactions()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StatementActivity)
            adapter = TransactionAdapter(transactions, this@StatementActivity)
        }
    }

    private fun configGoBackButton() {
        val btnGoBack = findViewById<Button>(R.id.btnGoBack)

        btnGoBack.setOnClickListener {
            finish()
        }
    }
}