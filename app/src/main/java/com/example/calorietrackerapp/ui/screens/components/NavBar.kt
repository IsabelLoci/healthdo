package com.example.calorietrackerapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.Icon
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset


@Composable
fun NavBar(navController: NavController){
    Box(modifier = Modifier.fillMaxSize()){
        // Bottom Navigation Bar
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0xFFF5F8F5))
                .padding(8.dp)
                .drawBehind {
                    drawLine(
                        color = Color(0xFF55745D),
                        start = Offset(0f, 0f),
                        end = Offset(size.width, 0f),
                        strokeWidth = 2.dp.toPx()
                    )
                }
            ,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextButton(onClick = { navController.navigate("home") }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Home, contentDescription = "Home", tint = Color(0xFF55745D), modifier = Modifier.size(28.dp))
                    Text("Today's Calories", color = Color(0xFF55745D))
                }
            }
            TextButton(onClick = { navController.navigate("searchMeal") }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.Search, contentDescription = "Search Meal", tint = Color(0xFF55745D), modifier = Modifier.size(28.dp))
                    Text("Search Meal", color = Color(0xFF55745D))
                }
            }
            TextButton(onClick = { navController.navigate("dailySummary") }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.DateRange, contentDescription = "Daily Summary", tint = Color(0xFF55745D), modifier = Modifier.size(28.dp))
                    Text("Daily Summary", color = Color(0xFF55745D))
                }
            }
        }
    }

}