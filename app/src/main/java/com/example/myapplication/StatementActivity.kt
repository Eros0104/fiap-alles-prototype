package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.services.MovementService

class StatementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statement)

        fetchMovements()
        configGoBackButton()
    }

    private fun fetchMovements() {
        val recyclerView = findViewById<RecyclerView>(R.id.movementRecyclerView)
        val movementService = MovementService()
        val movements = movementService.getMovements()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@StatementActivity)
            adapter = MovementAdapter(movements)
        }
    }

    private fun configGoBackButton() {
        val btnGoBack = findViewById<Button>(R.id.btnGoBack)

        btnGoBack.setOnClickListener {
            finish()
        }
    }
}