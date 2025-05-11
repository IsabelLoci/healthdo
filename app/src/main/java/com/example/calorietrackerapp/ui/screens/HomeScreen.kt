package com.example.calorietrackerapp.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.calorietrackerapp.ui.AppViewModelProvider
import com.example.calorietrackerapp.ui.screens.components.CalorieSummaryDashboard
import com.example.calorietrackerapp.ui.screens.components.MealRecordContainer
import com.example.calorietrackerapp.ui.viewmodel.MealSearchViewModel
import com.example.calorietrackerapp.ui.screens.components.NavBar
import com.example.calorietrackerapp.ui.screens.components.ScreenTitle
import com.example.calorietrackerapp.ui.screens.components.SearchMealFeature
import com.example.calorietrackerapp.ui.viewmodel.HomeScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)) {

    val dailySummary = viewModel.todaySummary.collectAsState()
    val todayMealRecords = viewModel.getTodayMeals().collectAsState(initial = emptyList())

    val mealList = todayMealRecords.value ?: emptyList()


    Log.d("From HomeScreen", todayMealRecords.toString())

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // Today header
            ScreenTitle("Today")

            Spacer(modifier = Modifier.height(20.dp))

            // Calorie Summary Dashboard to report to the user about their status
            CalorieSummaryDashboard(dailySummary.value)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Today's Meals:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            if (mealList.isEmpty()){
                Text("Is it time to eat something?")
            }
            else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(mealList) { mealRecord ->
                        MealRecordContainer(mealRecordWithFoods = mealRecord)
                    }
                }
            }

        }

        FloatingActionButton(
            onClick = { navController.navigate("AddNewMealRecord") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .zIndex(1f)
                .padding(end = 16.dp, bottom = 72.dp),
            containerColor = Color(0xFF55745D),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }


        NavBar(navController)
    }
}
