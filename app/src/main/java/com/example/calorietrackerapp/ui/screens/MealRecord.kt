package com.example.calorietrackerapp.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calorietrackerapp.ui.screens.components.NavBar

@Composable
fun MealRecord (navController: NavController){
    Text(
        text = "Meal Recorder!",
        fontSize = 24.sp
    )

    NavBar(navController)
}