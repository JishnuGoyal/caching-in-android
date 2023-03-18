package com.example.cachinginandroid.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cachinginandroid.util.Screen

@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Random dog generator!", Modifier.padding(bottom = 80.dp))

        Button(
            onClick = {
                navController.navigate(Screen.GenerateDogsScreen.route)
            },
              colors = ButtonDefaults.buttonColors(containerColor = Color(66,134,244))


        ) {
            Text(text = "Generate dogs")
        }

        Button(
            onClick = {
                Log.d("button", "pressed")
                navController.navigate(Screen.CachedDogsScreen.route)
            },
              colors = ButtonDefaults.buttonColors(containerColor = Color(66,134,244))


        ) {
            Text(text = "My recently generated dogs")
        }
    }
}