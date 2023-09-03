package com.example.myapplication.ui.home

import android.content.Intent
import android.icu.text.NumberFormat
import android.icu.util.Currency
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myapplication.CardActivity
import com.example.myapplication.R
import com.example.myapplication.RechargeActivity
import com.example.myapplication.StatementActivity
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.models.Category
import com.example.myapplication.services.TransactionService

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root: View = binding.root

        initializeViewModel()
        setButtons()

        return root
    }

    override fun onResume() {
        super.onResume()

        setBalance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeViewModel = viewModel

        setBalance()
    }

    private fun initializeViewModel() {
        val txtBalance: TextView = binding.txtBalanceValue
        val txtTransportBalance: TextView = binding.txtTransportBalance
        val txtMarketBalance: TextView = binding.txtMarketBalance
        val txtRestaurantBalance: TextView = binding.txtRestaurantBalance
        val txtGeneralBalance: TextView = binding.txtGeneralBalance


        viewModel.textBalance.observe(viewLifecycleOwner) {
            txtBalance.text = it
        }

        viewModel.setTextBalance("")

        viewModel.textBalances.forEach { (category, liveData) ->
            liveData.observe(viewLifecycleOwner) {
                when (category) {
                    Category.MARKET -> txtMarketBalance.text = it
                    Category.TRANSPORT -> txtTransportBalance.text = it
                    Category.RESTAURANT -> txtRestaurantBalance.text = it
                    Category.GENERAL -> txtGeneralBalance.text = it
                }
            }
            viewModel.setTextCategoryBalance(category, "")
        }
    }

    private fun setBalance() {
        val transactionService = TransactionService
        val nf: NumberFormat = NumberFormat.getCurrencyInstance()
        nf.maximumFractionDigits = 2
        nf.currency = Currency.getInstance("BRL")

        val balanceValue = nf.format(transactionService.getBalance())
        viewModel.setTextBalance(balanceValue)

        Category.values().forEach { category ->
            val balanceValue = transactionService.getBalanceByCategory(category)
            val formattedBalanceValue = nf.format(balanceValue)
            val label = getBalanceLabel(category)

            viewModel.setTextCategoryBalance(category, label + formattedBalanceValue)
        }
    }

    private fun getBalanceLabel(category: Category): String {
        return when (category) {
            Category.MARKET -> "Mercado: "
            Category.TRANSPORT -> "Transporte: "
            Category.RESTAURANT -> "Refeição: "
            Category.GENERAL -> "Saldo livre: "
        }
    }

    private fun setButtons() {
        val btnStatement: Button = binding.btnStatement
        val btnRecharge: Button = binding.btnRecharge
        val btnCard: Button = binding.btnCard

        btnStatement.setOnClickListener {
            val intent = Intent(activity, StatementActivity::class.java)
            startActivity(intent)
        }

        btnRecharge.setOnClickListener {
            val intent = Intent(activity, RechargeActivity::class.java)
            startActivity(intent)
        }

        btnCard.setOnClickListener {
            val intent = Intent(activity, CardActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
