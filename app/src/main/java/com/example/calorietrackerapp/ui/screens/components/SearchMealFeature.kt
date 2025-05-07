package com.example.calorietrackerapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calorietrackerapp.viewmodel.MealSearchViewModel

@Composable
fun SearchMealFeature(){
    // viewmodel connect to the edamam API
    val viewModel: MealSearchViewModel = viewModel()
    val results by viewModel.searchResults.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    // search bar for the meal
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color(0xFFF5F5F5))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                if (it.isNotBlank()) viewModel.searchMeal(it)
            },
            placeholder = { Text("Find your meal's calories here...") },
            modifier = Modifier.weight(1f),
            maxLines = 1,
            singleLine = true
        )
        IconButton(onClick = { /* TODO: Search Action */ }) {
            Icon(Icons.Default.Search, contentDescription = "Search")
        }
    }

    Spacer(modifier = Modifier.height(24.dp))

    // Today's Meals Title
    Text(
        text = "Today's Meals",
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    // Meal List
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(results) { meal ->
            Text(
                text = "${meal.name}: ${meal.calories} Calories",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}