package com.example.calorietrackerapp

data class EdamamResponse(
    val hints: List<Hint>
)

data class Hint(
    val food: Food
)

data class Food(
    val label: String,
    val nutrients: Nutrients
)

data class Nutrients(
    val ENERC_KCAL: Double
)
