package com.example.myapplication.models

import java.time.LocalDateTime

enum class TransactionType {
    DEBIT,
    CREDIT
}
data class Transaction(
    val id: String,
    val value: Double,
    val description: String,
    val transactionType: TransactionType,
    val date: LocalDateTime
)