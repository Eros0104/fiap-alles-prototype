package com.example.myapplication.services

import com.example.myapplication.models.Transaction
import com.example.myapplication.models.TransactionType
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
        val credit = Transaction(
            "credit",
            2000.0,
            "Credit Account",
            TransactionType.CREDIT
        )

        transactions.add(credit)
    }

    private fun generateDebitTransactions () {
        repeat(numberOfMovements) {index ->
            val randomIndex = Random.nextInt(movementsDescriptions.size);
            val randomMovementDescription = movementsDescriptions[randomIndex]
            val randomValue = Random.nextDouble(minValue, maxValue)
            val movementId = index.toString()

            val movement = Transaction(
                movementId,
                randomValue,
                randomMovementDescription,
                TransactionType.DEBIT
            );

            transactions.add(movement)
        }
    }

    fun getTransactions(): MutableList<Transaction> {
        return transactions
    }

    fun getBalance(): Double {
        var total = 0.0;

        transactions.forEach {transaction ->
            if (transaction.transactionType == TransactionType.CREDIT) {
                total += transaction.value
            } else {
                total -= transaction.value
            }
        }

        return total;
    }
}