package com.example.myapplication.models

import java.time.LocalDateTime

enum class Category {
    MARKET,
    RESTAURANT,
    TRANSPORT,
    FREE
}
open class Transaction(
    val id: String,
    val value: Double,
    val description: String,
    val date: LocalDateTime
)

class DebitTransaction(
    id: String,
    value: Double,
    description: String,
    date: LocalDateTime
) : Transaction(id, value, description, date)

class CreditTransaction(
    id: String,
    value: Double,
    description: String,
    date: LocalDateTime,
    val category: Category
) : Transaction(id, value, description, date)