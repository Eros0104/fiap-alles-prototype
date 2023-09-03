package com.example.myapplication.models

import java.time.LocalDateTime

enum class TransactionType {
    CREDIT,
    DEBIT
}
enum class Category {
    MARKET,
    RESTAURANT,
    TRANSPORT,
    GENERAL
}
class Transaction(
    val id: String,
    val value: Double,
    val description: String,
    val date: LocalDateTime,
    val category: Category,
    val transactionType: TransactionType
)