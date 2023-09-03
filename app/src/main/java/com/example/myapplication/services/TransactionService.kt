package com.example.myapplication.services

import com.example.myapplication.models.Category
import com.example.myapplication.models.Transaction
import com.example.myapplication.models.TransactionType
import java.time.LocalDateTime
import kotlin.random.Random

val movementsDescriptions = listOf<String>("Market", "Taxi", "Food", "Games")
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

    private fun generateCredit () {
        val id = (transactions.size + 1).toString()
        val credit = Transaction(
            id,
            1000.0,
            "Company",
            LocalDateTime.now().minusDays(numberOfMovements.toLong()),
            Category.RESTAURANT,
            TransactionType.CREDIT
        )

        val credit2 = Transaction(
            id,
            1000.0,
            "Company",
            LocalDateTime.now().minusDays(numberOfMovements.toLong()),
            Category.TRANSPORT,
            TransactionType.CREDIT
        )

        val credit3 = Transaction(
            id,
            500.0,
            "Company",
            LocalDateTime.now().minusDays(numberOfMovements.toLong()),
            Category.MARKET,
            TransactionType.CREDIT
        )

        transactions.add(credit)
        transactions.add(credit2)
        transactions.add(credit3)
        restaurantBalance += credit.value;
        transportBalance += credit2.value;
        marketBalance += credit3.value
    }

    private fun generateDebitTransactions() {
        repeat(numberOfMovements) { index ->
            val randomIndex = Random.nextInt(movementsDescriptions.size)
            val randomMovementDescription = movementsDescriptions[randomIndex]
            val randomValue = Random.nextDouble(minValue, maxValue)
            val movementId = index.toString()
            val date = LocalDateTime.now().minusDays(index.toLong())

            val randomCategory = Category.values().random()

            val movement = Transaction(
                movementId,
                randomValue,
                randomMovementDescription,
                date,
                randomCategory,
                TransactionType.DEBIT
            )

            transactions.add(movement)
            subtractCategorizedBalance(movement.value, randomCategory)
        }
    }

    fun getTransactions(): MutableList<Transaction> {
        return transactions.sortedByDescending  {it.date}.toMutableList()
    }

    private fun subtractCategorizedBalance(value: Double, category: Category) {

                if (category == Category.GENERAL) {
                    balance -= value;
                } else {
                    val availableBalance = getBalanceByCategory(category)

                    val subtractedBalance = availableBalance - value;

                    if (subtractedBalance < 0) {
                        balance += subtractedBalance
                        when (category) {
                            Category.MARKET -> marketBalance = 0.0
                            Category.TRANSPORT -> transportBalance = 0.0
                            Category.RESTAURANT -> restaurantBalance = 0.0
                            else -> throw IllegalStateException("Invalid category param value");
                        }
                    } else {
                        when (category) {
                            Category.MARKET -> marketBalance = subtractedBalance
                            Category.TRANSPORT -> transportBalance = subtractedBalance
                            Category.RESTAURANT -> restaurantBalance = subtractedBalance
                            else -> throw IllegalStateException("Invalid category param value")
                        }
                    }
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
        return transactions.filter {transaction ->
            transaction.transactionType == TransactionType.CREDIT
        }.sortedByDescending { it.date}.toMutableList()
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