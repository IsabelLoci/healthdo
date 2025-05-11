package com.example.calorietrackerapp.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ScreenTitle(title: String, navController: NavController? = null) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth().padding(top = 15.dp)
    ) {
        if (navController != null){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF213F28)
        )
    }

    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        thickness = 2.dp,
        color = Color(0xFF213F28)
    )
}
