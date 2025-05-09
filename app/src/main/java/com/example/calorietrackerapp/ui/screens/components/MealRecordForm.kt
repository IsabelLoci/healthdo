package com.example.calorietrackerapp.ui.screens.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.HistoricalChange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.data.entity.MealRecord
import com.example.calorietrackerapp.ui.viewmodel.MealRecordUiState
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

// TODO: FIX THE ERROR OF CAN'T SCROLL DOWN WHEN TOO MUCH FOOD SELECTED

@RequiresApi(Build.VERSION_CODES.O)
fun isValidDate(input: String): Boolean {
    return try {
        LocalDate.parse(input, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun isValidTime(input: String): Boolean {
    return try {
        LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"))
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MealRecordForm(
    uiState: MealRecordUiState,
    onValueChange: (MealRecordUiState) -> Unit,
    checkboxConfig: CheckboxConfig,
    onSubmit: () -> Unit,
    isEditing: Boolean = false,
    onDelete: (() -> Unit)? = null
) {

    val isDateValid = isValidDate(uiState.date)
    val isTimeValid = isValidTime(uiState.time)

    val isFormValid = isDateValid && isTimeValid && checkboxConfig.selectedFoodItems.isNotEmpty()



    Column(modifier = Modifier.padding(16.dp)) {

        // adjust the form type to update meal record or add meal record
        Text(
            text = if (isEditing) "Edit Meal Record" else "Add Meal Record",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Date input
        OutlinedTextField(
            value = uiState.date,
            onValueChange = { onValueChange(uiState.copy(date = it)) },
            label = { Text("Date (e.g. 03/02/2025)") },
            modifier = Modifier.fillMaxWidth()
        )
        if (!isDateValid && uiState.date.isNotBlank()) {
            Text("Please enter a valid date in MM/dd/yyyy format", color = Color.Red, fontSize = 12.sp)
        }

        // Time input
        OutlinedTextField(
            value = uiState.time,
            onValueChange = { onValueChange(uiState.copy(time = it)) },
            label = { Text("Meal Time (e.g. 12:00)") },
            modifier = Modifier.fillMaxWidth()
        )
        if (!isTimeValid && uiState.time.isNotBlank()) {
            Text("Please enter a valid time in HH:mm format", color = Color.Red, fontSize = 12.sp)
        }


        Spacer(Modifier.height(12.dp))


        // The food the user has selected already
        Column(){
            Text("Select Food Items: ", fontWeight = FontWeight.SemiBold)
            if (checkboxConfig.selectedFoodItems.isEmpty()) {
                Text("There's no food selected!", modifier = Modifier.fillMaxWidth().padding(top = 8.dp),textAlign = TextAlign.Center)
            }
            else{
                // display all the selected food
                LazyColumn(
                    verticalArrangement = Arrangement.Top
                ) {
                    items(checkboxConfig.selectedFoodItems) { food ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { checkboxConfig.onToggleFoodItem(food) }
                        ) {
                            Checkbox(
                                checked = checkboxConfig.selectedFoodItems.contains(food),
                                onCheckedChange = { checkboxConfig.onToggleFoodItem(food) }
                            )
                            Text("${food.foodName} (${food.kcal} kcal)")
                        }
                    }


                }
            }

            // Calorie total
            Text(
                text = "Total Calories: ${checkboxConfig.totalCalories}",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Center
            )

        }

        Spacer(Modifier.height(16.dp))


        // the search meal feature that allows user to search and find their food
        // take param of checkboxConfig to state the feature want the foodItems as the checkbox
        SearchMealFeature(checkboxConfig = checkboxConfig)

        Spacer(Modifier.height(12.dp))


        Spacer(Modifier.height(16.dp))


        Button(
            onClick = {
                if (isFormValid) {
                    Log.d("success", "Date: ${uiState.date}, Time: ${uiState.time}")
                    Log.d("success", "Selected Foods: ${checkboxConfig.selectedFoodItems.joinToString { it.foodName }}")
                    onSubmit()
                } else {
                    Log.d("error", "Invalid input: date or time format incorrect")
                }
            },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (isEditing) "Update Meal" else "Save Meal")
        }


        if (isEditing && onDelete != null) {
            TextButton(
                onClick = onDelete,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Text("Delete Meal", color = Color.Red)
            }
        }
    }
}

