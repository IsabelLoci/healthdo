package com.example.calorietrackerapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.ui.screens.components.CalorieSummaryDashboard
import com.example.calorietrackerapp.ui.viewmodel.MealSearchViewModel
import com.example.calorietrackerapp.ui.screens.components.NavBar
import com.example.calorietrackerapp.ui.screens.components.SearchMealFeature

@Composable
fun HomeScreen(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Today header
            Text(
                text = "Today",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Calorie Summary Dashboard to report to the user about their status
            CalorieSummaryDashboard(500, 2500, 0.4f, 0.2f, 0.2f)

            Spacer(modifier = Modifier.height(24.dp))

            SearchMealFeature()

        }

        FloatingActionButton(
            onClick = { navController.navigate("AddNewMealRecord")},
            modifier = Modifier.align(Alignment.BottomEnd).padding(end=16.dp, bottom = 72.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }

        NavBar(navController)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}