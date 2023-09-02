package com.example.myapplication.services

import com.example.myapplication.models.Category
import com.example.myapplication.models.CreditTransaction
import com.example.myapplication.models.DebitTransaction
import com.example.myapplication.models.Transaction
import java.time.LocalDateTime
import kotlin.random.Random

val movementsDescriptions = listOf<String>("Market", "Taxi", "Food", "Games")
const val numberOfMovements = 30;
const val minValue = 5.0;
const val maxValue = 100.0;

object TransactionService {
    private val transactions = mutableListOf<Transaction>()

    init {
        generateDebitTransactions()
        generateCredit()
    }

    private fun generateCredit () {
        val id = (transactions.size + 1).toString()
        val credit = CreditTransaction(
            id,
            2000.0,
            "Company",
            LocalDateTime.now().minusDays(numberOfMovements.toLong()),
            Category.RESTAURANT
        )

        transactions.add(credit)
    }

    private fun generateDebitTransactions () {
        repeat(numberOfMovements) {index ->
            val randomIndex = Random.nextInt(movementsDescriptions.size);
            val randomMovementDescription = movementsDescriptions[randomIndex]
            val randomValue = Random.nextDouble(minValue, maxValue)
            val movementId = index.toString()
            val date = LocalDateTime.now().minusDays(index.toLong())

            val movement = DebitTransaction(
                movementId,
                randomValue,
                randomMovementDescription,
                date
            );

            transactions.add(movement)
        }
    }

    fun getTransactions(): MutableList<Transaction> {
        return transactions.sortedByDescending  {it.date}.toMutableList()
    }

    fun getBalance(): Double {
        var total = 0.0;

        transactions.forEach {transaction ->
            if (transaction is CreditTransaction) {
                total += transaction.value
            } else {
                total -= transaction.value
            }
        }

        return total;
    }

    fun getRecharges(): MutableList<Transaction> {
        return transactions.filter {transaction ->
            transaction is CreditTransaction
        }.sortedByDescending { it.date}.toMutableList()
    }

    fun recharge(value: Double, description: String) {
        val id = (transactions.size + 1).toString()
        val newTransaction = CreditTransaction(
            id, value, description, LocalDateTime.now(), Category.FREE
        )

        transactions.add(newTransaction)
    }
}