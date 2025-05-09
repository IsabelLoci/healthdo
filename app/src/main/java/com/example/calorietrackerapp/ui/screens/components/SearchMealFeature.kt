package com.example.calorietrackerapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Checkbox
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
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.ui.AppViewModelProvider
import com.example.calorietrackerapp.ui.viewmodel.MealSearchViewModel

data class CheckboxConfig(
    val selectedFoodItems: List<FoodItem>,
    val onToggleFoodItem: (FoodItem) -> Unit,
    val totalCalories: Int,
)

@Composable
fun SearchMealFeature(viewModel: MealSearchViewModel = viewModel(factory = AppViewModelProvider.Factory), checkboxConfig: CheckboxConfig? = null){

    //TODO after a checkbox being selected, it should stay onto the screen!!!

    // viewmodel connect to the backend database
    val viewModel: MealSearchViewModel = viewModel()
    val results by viewModel.searchResults.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    Column{
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
                },
                placeholder = { Text("Find your meal's calories here...") },
                modifier = Modifier.weight(1f),
                maxLines = 1,
                singleLine = true
            )
            IconButton(onClick = { if (searchQuery.isNotBlank()) viewModel.searchMeal(searchQuery) }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Meal List
        // Meal List will display either checkbox or mealinfo container
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // if the checkboxconfig exist, then display the fooditem as the check box
            if(checkboxConfig != null){
                items(results) { food ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { checkboxConfig.onToggleFoodItem(food) }
                            .padding(vertical = 6.dp)
                    ) {
                        Checkbox(
                            checked = checkboxConfig.selectedFoodItems.contains(food),
                            onCheckedChange = { checkboxConfig.onToggleFoodItem(food) }
                        )
                        Text("${food.foodName} (${food.kcal} kcal)")
                    }
                }
            }else{
                // if checkbox doesn't exist then display simple meal info
                items(results) { meal ->
                    MealInfoContainer(meal)
                }
            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun SearchMealFeaturePreview() {
    SearchMealFeature()
}
