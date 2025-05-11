package com.example.calorietrackerapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.ui.screens.components.NavBar
import com.example.calorietrackerapp.ui.screens.components.ScreenTitle
import com.example.calorietrackerapp.ui.screens.components.SearchMealFeature

@Composable
fun SearchMeal (navController: NavController){

    Column (
        modifier = Modifier.padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(20.dp))

        // Search header
        ScreenTitle("Search Meal")
        SearchMealFeature()
    }

    NavBar(navController)
}
