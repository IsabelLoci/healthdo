package com.example.calorietrackerapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.ui.screens.components.MealInfoContainer
import com.example.calorietrackerapp.ui.screens.components.NavBar
import com.example.calorietrackerapp.ui.screens.components.SearchMealFeature
import com.example.calorietrackerapp.ui.viewmodel.MealSearchViewModel

@Composable
fun MealRecord (navController: NavController){
    val foodList = listOf(
        FoodItem(1,foodName = "Banana", kcal = 89, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/2324px-Banana-Single.jpg"),
        FoodItem(2,foodName = "Grilled Chicken Breast", kcal = 165, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/2324px-Banana-Single.jpg"),
        FoodItem(3,foodName = "Avocado", kcal = 160, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/2324px-Banana-Single.jpg"),
        FoodItem(4,foodName = "Boiled Egg", kcal = 78, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/2324px-Banana-Single.jpg"),
        FoodItem(5,foodName = "Steamed Broccoli", kcal = 55, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Banana-Single.jpg/2324px-Banana-Single.jpg"),
    )

    LazyColumn (verticalArrangement = Arrangement.spacedBy(8.dp)){
        items(foodList){food ->
            MealInfoContainer(food)
        }
    }

    NavBar(navController)
}

@Preview(showBackground = true)
@Composable
fun MealRecordPreview() {
    val navController = rememberNavController()
    MealRecord(navController)
}