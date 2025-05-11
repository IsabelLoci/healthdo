package com.example.calorietrackerapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calorietrackerapp.ui.screens.AddNewMealRecord
import com.example.calorietrackerapp.ui.screens.AuthLandingScreen
import com.example.calorietrackerapp.ui.screens.DailySummaryScreen
import com.example.calorietrackerapp.ui.screens.HomeScreen
import com.example.calorietrackerapp.ui.screens.RegisterScreen
import com.example.calorietrackerapp.ui.screens.SearchMeal
import com.example.calorietrackerapp.ui.screens.SummaryDetailPage
import com.example.calorietrackerapp.ui.theme.CalorieTrackerAppTheme
import kotlinx.datetime.LocalDate


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalorieTrackerAppTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "home") {
                    composable("authLanding") { AuthLandingScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("home") { HomeScreen(navController)}
                    composable("searchMeal") { SearchMeal(navController) }
                    composable("dailySummary") { DailySummaryScreen(navController) }
                    composable("addNewMealRecord") { AddNewMealRecord(navController) }

                    // call summary detail page and require a "date" parameter
                    composable(
                        "summaryDetailPage/{date}",
                        arguments = listOf(navArgument("date") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val dateString = backStackEntry.arguments?.getString("date") ?: ""
                        SummaryDetailPage(
                            navController,
                            date = LocalDate.parse(dateString)
                        )
                    }




                }
            }
        }
    }

}

