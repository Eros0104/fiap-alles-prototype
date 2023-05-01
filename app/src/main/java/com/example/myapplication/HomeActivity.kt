package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnStatement = findViewById<Button>(R.id.btnStatement)
        val btnRecharge = findViewById<Button>(R.id.btnRecharge)
        val btnCard = findViewById<Button>(R.id.btnCard)

        btnStatement.setOnClickListener {
            val intent = Intent(this, StatementActivity::class.java)
            startActivity(intent)
        }

        btnRecharge.setOnClickListener {
            val intent = Intent(this, RechargeActivity::class.java)
            startActivity(intent)
        }

        btnCard.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }
    }
}