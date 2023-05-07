package com.example.myapplication.models

import java.time.LocalDateTime

data class Card(
    val id: String,
    val number: String,
    val name: String,
    val securityCode: String,
)