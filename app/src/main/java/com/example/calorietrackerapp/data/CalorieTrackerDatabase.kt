package com.example.calorietrackerapp.data

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.calorietrackerapp.data.dao.*
import com.example.calorietrackerapp.data.entity.*
import com.example.calorietrackerapp.data.Converters

@Database(
    entities = [
        FoodItem::class,
        MealRecord::class,
        DailySummary::class,
        FoodItemMealRecordCrossRef::class
               ],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class CalorieTrackerDatabase : RoomDatabase() {
    abstract fun foodItemDao(): FoodItemDao
    abstract fun mealRecordDao(): MealRecordDao
    abstract fun dailySummaryDao(): DailySummaryDao
    abstract fun foodItemMealRecordCrossRefDao(): FoodItemMealRecordCrossRefDao

    companion object{
        @Volatile
        private var Instance: CalorieTrackerDatabase? = null

        fun getDatabase(context: Context): CalorieTrackerDatabase {
            return Instance?: synchronized(this){
                Room.databaseBuilder(context, CalorieTrackerDatabase::class.java, "carlorie_tracker_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also{ Instance = it }
            }
        }
    }
}