package com.example.calorietrackerapp.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.calorietrackerapp.ui.AppViewModelProvider
import com.example.calorietrackerapp.ui.screens.components.CalorieSummaryDashboard
import com.example.calorietrackerapp.ui.screens.components.NavBar
import com.example.calorietrackerapp.ui.screens.components.ScreenTitle
import com.example.calorietrackerapp.ui.viewmodel.DailySummaryViewModel
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.header.DefaultMonthHeader
import io.github.boguszpawlowski.composecalendar.rememberCalendarState
import java.time.LocalDate


@Composable
fun DayContent(
    dayState: DayState<*>,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color(0xFF55745D) else Color.Transparent
    val textColor = if (isSelected) Color.White else Color.Black

    Column {

        Box(
            modifier = Modifier
                .padding(4.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = dayState.date.dayOfMonth.toString(),
                color = textColor
            )
        }


    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailySummaryScreen(
    navController: NavController,
    viewModel: DailySummaryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val summary by viewModel.summaryState
    val calendarState = rememberCalendarState()
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(Unit) {
        viewModel.onDateSelected(selectedDate)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(top=40.dp, start=8.dp, end=8.dp, )) {

        Spacer(modifier = Modifier.height(20.dp))

        // Daily Summary header
        ScreenTitle("Daily Summary")
        Spacer(modifier = Modifier.height(20.dp))

        StaticCalendar(
            calendarState = calendarState,
            dayContent = { dayState ->
                DayContent(
                    dayState = dayState,
                    isSelected = dayState.date == selectedDate,
                    onClick = {
                        selectedDate = dayState.date
                        viewModel.onDateSelected(dayState.date)
                    }
                )
            },
            monthHeader = { monthState ->
                DefaultMonthHeader(
                    monthState = monthState,
                    modifier = Modifier.padding(bottom = 35.dp)
                )
            },
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(420.dp)
        )


        Spacer(modifier = Modifier.padding(8.dp))


        summary?.let {
            CalorieSummaryDashboard(it)
        } ?: Text("No data for ${selectedDate}")
    }

    NavBar(navController)
}
