package com.example.calorietrackerapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.calorietrackerapp.data.entity.DailySummary
import com.example.calorietrackerapp.ui.AppViewModelProvider
import com.example.calorietrackerapp.ui.screens.components.CalorieSummaryDashboard
import com.example.calorietrackerapp.ui.screens.components.MealRecordContainer
import com.example.calorietrackerapp.ui.screens.components.NavBar
import com.example.calorietrackerapp.ui.screens.components.ScreenTitle
import com.example.calorietrackerapp.ui.viewmodel.DailySummaryViewModel
import com.example.calorietrackerapp.ui.viewmodel.SummaryDetailViewModel
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import kotlinx.datetime.toJavaLocalDate
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SummaryDetailPage(
    navController: NavController,
    date: kotlinx.datetime.LocalDate,
    summaryViewModel: SummaryDetailViewModel = viewModel(factory = AppViewModelProvider.Factory),
    dailySummaryViewModel: DailySummaryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val dateMealRecords = summaryViewModel.getSelectedDateMeals(date).collectAsState(initial = emptyList())
    val mealList = dateMealRecords.value ?: emptyList()


    val summary by dailySummaryViewModel.summaryState
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(Unit) {
        dailySummaryViewModel.onDateSelected(selectedDate)
    }

    Column(modifier = Modifier.padding(top = 40.dp, start = 8.dp, end = 8.dp)) {
        ScreenTitle(date.toString(), navController)

        Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally){
            summary?.let { CalorieSummaryDashboard(it) }

            Spacer(modifier = Modifier.padding(13.dp))

            Text(
                text = "Meal Record",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(8.dp),
                color = Color(0xFF55745D)
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




        Spacer(modifier = Modifier.height(16.dp))
        NavBar(navController)
    }
}
