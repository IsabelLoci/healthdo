package com.example.calorietrackerapp.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.calorietrackerapp.data.entity.FoodItem

@Composable
fun MealInfoContainer(foodItem: FoodItem) {
    // TODO: Add a feature to allow user to click on the container and pop up to ask the user to add this in today's consumption

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = foodItem.foodName, modifier = Modifier.padding(horizontal = 3.dp))
            Text(text = "${foodItem.kcal.toString()} kcal", modifier = Modifier.padding(horizontal = 3.dp))

        }

        Spacer(modifier = Modifier.height(4.dp)) // spacing above the line

        HorizontalDivider(
            color = Color.Black,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(0.6f)
        )
    }
}

