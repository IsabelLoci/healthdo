package com.example.calorietrackerapp.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.ui.AppViewModelProvider
import com.example.calorietrackerapp.ui.screens.components.CheckboxConfig
import com.example.calorietrackerapp.ui.screens.components.MealRecordForm
import com.example.calorietrackerapp.ui.screens.components.ScreenTitle
import com.example.calorietrackerapp.ui.viewmodel.AddMealRecordViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddNewMealRecord(navController: NavController,
                     viewModel: AddMealRecordViewModel = viewModel(factory = AppViewModelProvider.Factory)
){

    Box(modifier = Modifier.padding(3.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            ScreenTitle("Add New Meal", navController)
            MealRecordForm(
                uiState = viewModel.uiState,
                onValueChange = viewModel::updateUiState,
                checkboxConfig = CheckboxConfig(
                    selectedFoodItems = viewModel.selectedFoodItems,
                    onToggleFoodItem = viewModel::toggleFoodItem,
                    totalCalories = viewModel.selectedFoodItems.sumOf { it.kcal },
                ),
                onSubmit = { viewModel.saveMeal { navController.popBackStack() } }
            )

        }
    }

}
