package com.example.myapplication

import android.content.Intent
import android.graphics.Typeface
import android.icu.lang.UProperty.INT_START
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
import com.example.myapplication.services.TransactionService


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setButtons()
    }

    override fun onStart() {
        super.onStart()

        setBalance()
    }

    private fun setBalance() {
        val transactionService = TransactionService
        val tvBalance = findViewById<TextView>(R.id.txtBalance)
        val nf: NumberFormat = NumberFormat.getCurrencyInstance()
        nf.maximumFractionDigits = 2
        nf.currency = Currency.getInstance("BRL");

        val balance = getString(R.string.balance) + " "
        val balanceValue = nf.format(transactionService.getBalance())
        val styledString = SpannableStringBuilder().bold { append(balance) }.append(balanceValue)

        tvBalance.text = styledString
    }

    private fun setButtons() {
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
