package com.example.myapplication.services

import com.example.myapplication.models.Card

class CardService {
    fun getCard(): Card {
        return Card("1", "1234 5678 9000", "Katia Doe", "123")
    }
}