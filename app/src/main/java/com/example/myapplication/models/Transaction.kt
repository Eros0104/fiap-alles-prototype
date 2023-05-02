package com.example.myapplication.models

enum class TransactionType {
    DEBIT,
    CREDIT
}
data class Transaction(
    val id: String,
    val value: Double,
    val description: String,
    val transactionType: TransactionType
)