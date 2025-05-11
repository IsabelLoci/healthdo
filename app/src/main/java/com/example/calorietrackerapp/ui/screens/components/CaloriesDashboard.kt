package com.example.calorietrackerapp.ui.screens.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calorietrackerapp.data.entity.DailySummary

@Composable
fun CalorieProgressBar(label: String, progress: Float, color: Color) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            LinearProgressIndicator(
                progress = progress,
                color = color,
                trackColor = Color.LightGray,
                modifier = Modifier
                    .weight(1f)
                    .height(6.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
        }
        Text(
            label,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun StaticCircularProgressBar(caloriesLeft: Int, progress: Float) {
    Box(
        modifier = Modifier.size(100.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(95.dp)) {
            val strokeWidth = 8.dp.toPx()
            val diameter = size.minDimension
            val radius = diameter / 2
            val center = Offset(radius, radius)
            val sweepAngle = 360 * progress

            // Background track (remaining progress)
            drawArc(
                color = Color(0xBFE8D0CE),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = Offset(0f, 0f),
                size = size,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Foreground progress
            drawArc(
                color = Color(0xFFF16F62),
                startAngle = -90f,
                sweepAngle = sweepAngle,
                useCenter = false,
                topLeft = Offset(0f, 0f),
                size = size,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )
        }

        Text(
            text = "$caloriesLeft kcal Left",
            color = Color.White,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}



@Composable
fun CalorieSummaryDashboard(
    dailySummary: DailySummary,
    calorieLimit: Int = 2000
) {
    val consumed = dailySummary.calorieConsumed
    val burned = dailySummary.calorieBurned
    val net = dailySummary.netCalorie

    val caloriesLeft = (calorieLimit - consumed).coerceAtLeast(0)
    val consumedProgress = consumed.toFloat() / calorieLimit
    val burnedProgress = burned.toFloat() / calorieLimit
    val netProgress = net.toFloat() / calorieLimit
    val overallProgress = ((calorieLimit-consumed).toFloat() / calorieLimit)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFF55745D))
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Circular progress (left side)
            StaticCircularProgressBar(
                caloriesLeft = caloriesLeft,
                progress = overallProgress
            )

            Spacer(modifier = Modifier.width(24.dp))

            // Progress bars (right side)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CalorieProgressBar("Consumed", consumedProgress, Color(0xFFFFF1B4))
                CalorieProgressBar("Burned", burnedProgress, Color(0xFFEEABA5))
                CalorieProgressBar("Net", netProgress, Color(0xFFB3F3CE))
            }
        }
    }
}


