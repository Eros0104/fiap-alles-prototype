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
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.text.bold
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.services.TransactionService
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)

//        binding.appBarMain.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
//                R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
//
//        setButtons()
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        setBalance()
//    }
//
//    private fun setBalance() {
//        val transactionService = TransactionService
//        val tvBalance = findViewById<TextView>(R.id.txtBalance)
//        val nf: NumberFormat = NumberFormat.getCurrencyInstance()
//        nf.maximumFractionDigits = 2
//        nf.currency = Currency.getInstance("BRL");
//
//        val balance = getString(R.string.balance) + " "
//        val balanceValue = nf.format(transactionService.getBalance())
//        val styledString = SpannableStringBuilder().bold { append(balance) }.append(balanceValue)
//
//        tvBalance.text = styledString
//    }
//
//    private fun setButtons() {
//        val btnStatement = findViewById<Button>(R.id.btnStatement)
//        val btnRecharge = findViewById<Button>(R.id.btnRecharge)
////        val btnCard = findViewById<Button>(R.id.btnCard)
//
//        btnStatement.setOnClickListener {
//            val intent = Intent(this, StatementActivity::class.java)
//            startActivity(intent)
//        }
//
//        btnRecharge.setOnClickListener {
//            val intent = Intent(this, RechargeActivity::class.java)
//            startActivity(intent)
//        }
//
////        btnCard.setOnClickListener {
////            val intent = Intent(this, CardActivity::class.java)
////            startActivity(intent)
////        }
//    }
}
