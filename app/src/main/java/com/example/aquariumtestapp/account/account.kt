package com.example.aquariumtestapp.account

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.aquariumtestapp.SupabaseViewModel

@Composable
fun account (viewModel: SupabaseViewModel = viewModel(), navController: NavHostController) {
    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
            onClick = {
                viewModel.logout(context, navController = navController)
            }) {
            Text(text = "Logout")
        }
    }
}