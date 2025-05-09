package com.example.calorietrackerapp.data

import android.content.Context
import androidx.room.Room
import com.example.calorietrackerapp.data.entity.FoodItem
import com.example.calorietrackerapp.data.repository.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

interface AppContainer {
    val foodItemRepository: FoodItemRepository
    val mealRecordRepository: MealRecordRepository
    val dailySummaryRepository: DailySummaryRepository
    val foodItemMealRecordCrossRefRepository : FoodItemMealRecordCrossRefRepository
}

class DefaultAppContainer(context: Context) : AppContainer {

    private val database: CalorieTrackerDatabase = CalorieTrackerDatabase.getDatabase(context)

    override val foodItemRepository: FoodItemRepository by lazy {
        OfflineFoodItemRepository(database.foodItemDao())
    }

    override val mealRecordRepository: MealRecordRepository by lazy {
        OfflineMealRecordRepository(database.mealRecordDao())
    }

    override val dailySummaryRepository: DailySummaryRepository by lazy {
        OfflineDailySummaryRepository(database.dailySummaryDao())
    }

    override val foodItemMealRecordCrossRefRepository : FoodItemMealRecordCrossRefRepository by lazy {
        OfflineFoodItemMealRecordCrossRefRepository(database.foodItemMealRecordCrossRefDao())
    }

    val mockFoodItems = listOf(
        FoodItem(foodName = "Banana", kcal = 89, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg"),
        FoodItem(foodName = "Egg", kcal = 78, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/7/7b/Egg_%28whole%29.jpg"),
        FoodItem(foodName = "Apple", kcal = 95, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg"),
        FoodItem(foodName = "Orange", kcal = 62, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"),
        FoodItem(foodName = "Avocado", kcal = 160, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/f/f9/Avocado_with_cross_section_edit.jpg"),
        FoodItem(foodName = "Rice", kcal = 200, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/6/60/Plain_rice.jpg"),
        FoodItem(foodName = "Chicken Breast", kcal = 165, imageUrl = "https://upload.wikimedia.org/wikipedia/commons/2/20/Grilled_Chicken_Breast.jpg")
    )


    init{
        CoroutineScope(Dispatchers.IO).launch {
            val existing = foodItemRepository.getAllFoodItems().first()
            if (existing.isEmpty()) {
                mockFoodItems.forEach {
                    foodItemRepository.insertFoodItem(it)
                }
            }
        }
    }
}
