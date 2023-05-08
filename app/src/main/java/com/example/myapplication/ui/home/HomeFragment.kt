package com.example.myapplication.ui.home

import android.content.Intent
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.CardActivity
import com.example.myapplication.R
import com.example.myapplication.RechargeActivity
import com.example.myapplication.StatementActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.services.TransactionService

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val txtWelcome: TextView = binding.txtWelcome
        homeViewModel.textWelcome.observe(viewLifecycleOwner) {
            txtWelcome.text = it
        }

        val txtBalance: TextView = binding.txtBalance
        homeViewModel.textBalance.observe(viewLifecycleOwner) {
            txtBalance.text = it
        }
        return root
    }

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

    private fun setButtons() {
        val btnStatement: Button = binding.btnStatement
        val btnRecharge: Button = binding.btnRecharge
        val btnCard: Button = binding.btnCard
//        val btnStatement = findViewById<Button>(R.id.btnStatement)
//        val btnRecharge = findViewById<Button>(R.id.btnRecharge)
//        val btnCard = findViewById<Button>(R.id.btnCard)

        btnStatement.setOnClickListener {
//            val intent = Intent(this, StatementActivity::class.java)
//            startActivity(intent)
        }

        btnRecharge.setOnClickListener {
//            val intent = Intent(this, RechargeActivity::class.java)
//            startActivity(intent)
        }

        btnCard.setOnClickListener {
//            val intent = Intent(this, CardActivity::class.java)
//            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
