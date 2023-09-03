package com.example.myapplication.services

import com.example.myapplication.models.Category
import com.example.myapplication.models.Transaction
import com.example.myapplication.models.TransactionType
import java.time.LocalDateTime
import kotlin.random.Random

data class DebitTransactionTemplate(
    val label: String,
    val category: Category
)

val movementsDescriptions = listOf(
    DebitTransactionTemplate("Market", Category.MARKET),
    DebitTransactionTemplate("Taxi", Category.TRANSPORT),
    DebitTransactionTemplate("Food", Category.RESTAURANT),
    DebitTransactionTemplate("Games", Category.GENERAL)
)
const val numberOfMovements = 30;
const val minValue = 5.0;
const val maxValue = 100.0;

object TransactionService {
    private val transactions = mutableListOf<Transaction>()
    private var balance = 0.0
    private var marketBalance = 0.0
    private var transportBalance = 0.0
    private var restaurantBalance = 0.0

    init {
        generateCredit()
        generateDebitTransactions()
    }

    private fun generateCredit() {
        generateCompanyCredit(Category.RESTAURANT, 1000.0)
        generateCompanyCredit(Category.TRANSPORT, 1000.0)
        generateCompanyCredit(Category.MARKET, 500.0)
        generateCompanyCredit(Category.GENERAL, 500.0)
    }

    private fun generateCompanyCredit(category: Category, value: Double) {
        val id = (transactions.size + 1).toString()
        val credit = Transaction(
            id,
            value,
            "Company",
            LocalDateTime.now().minusDays(numberOfMovements.toLong()),
            category,
            TransactionType.CREDIT
        )

        transactions.add(credit)
        addCategorizedBalance(credit.value, credit.category)
    }

    private fun generateDebitTransactions() {
        repeat(numberOfMovements) { index ->
            val randomIndex = Random.nextInt(movementsDescriptions.size)
            val randomTransactionTemplate = movementsDescriptions[randomIndex]
            val randomValue = Random.nextDouble(minValue, maxValue)
            val movementId = index.toString()
            val date = LocalDateTime.now().minusDays(index.toLong())

            val movement = Transaction(
                movementId,
                randomValue,
                randomTransactionTemplate.label,
                date,
                randomTransactionTemplate.category,
                TransactionType.DEBIT
            )

            transactions.add(movement)
            subtractCategorizedBalance(movement.value, randomTransactionTemplate.category)
        }
    }

    fun getTransactions(): MutableList<Transaction> {
        return transactions.sortedByDescending { it.date }.toMutableList()
    }

    private fun subtractCategorizedBalance(value: Double, category: Category) {
        if (category == Category.GENERAL) {
            balance -= value;
        } else {
            val availableBalance = getBalanceByCategory(category)

            val subtractedBalance = availableBalance - value;

            if (subtractedBalance < 0) {
                balance += subtractedBalance
                setCategoryBalance(0.0, category)
            } else {
                setCategoryBalance(subtractedBalance, category)
            }
        }

    }

    private fun addCategorizedBalance(value: Double, category: Category) {
        val currentBalance = getBalanceByCategory(category)
        val newBalance = currentBalance + value
        setCategoryBalance(newBalance, category)
    }

    private fun setCategoryBalance(value: Double, category: Category) {
        when (category) {
            Category.MARKET -> marketBalance = value
            Category.TRANSPORT -> transportBalance = value
            Category.RESTAURANT -> restaurantBalance = value
            Category.GENERAL -> balance = value
        }
    }

    fun getBalance(): Double {
        return balance + marketBalance + transportBalance + restaurantBalance;
    }

    fun getBalanceByCategory(category: Category): Double {
        return when (category) {
            Category.MARKET -> marketBalance
            Category.TRANSPORT -> transportBalance
            Category.RESTAURANT -> restaurantBalance
            Category.GENERAL -> balance
        }
    }

    fun getCreditTransactions(): MutableList<Transaction> {
        return transactions.filter { transaction ->
            transaction.transactionType == TransactionType.CREDIT
        }.sortedByDescending { it.date }.toMutableList()
    }

    fun recharge(value: Double, description: String) {
        val id = (transactions.size + 1).toString()
        val newTransaction = Transaction(
            id, value, description, LocalDateTime.now(), Category.GENERAL, TransactionType.CREDIT
        )

        transactions.add(newTransaction)
        balance += newTransaction.value
    }
}