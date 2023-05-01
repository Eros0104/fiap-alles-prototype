package com.example.myapplication.services

import com.example.myapplication.models.Movement
import kotlin.random.Random

val movementsDescriptions = listOf<String>("Market", "Taxi", "Food", "Games")
const val numberOfMovements = 30;
const val minValue = 5.0;
const val maxValue = 500.0;

class MovementService {
    private val movements = mutableListOf<Movement>()

    init {
        repeat(numberOfMovements) {index ->
            val randomIndex = Random.nextInt(movementsDescriptions.size);
            val randomMovementDescription = movementsDescriptions[randomIndex]
            val randomValue = Random.nextDouble(minValue, maxValue)
            val movementId = index.toString()

            val movement = Movement(movementId, randomValue, randomMovementDescription);
            movements.add(movement)
        }
    }

    fun getMovements(): MutableList<Movement> {
        return movements
    }
}