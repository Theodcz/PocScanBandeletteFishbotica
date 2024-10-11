package com.example.aquariumtestapp.connect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquariumtestapp.SupabaseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Register(
    viewModel: SupabaseViewModel = viewModel(),
    navController: androidx.navigation.NavHostController,
) {

    val context = LocalContext.current

    var displayName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(
            context,
            navController = navController,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        TextField(value = displayName, placeholder = {
            Text(text = "Enter display name")
        }, onValueChange = {
            displayName = it
        } )
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = userEmail,
            placeholder = {
                Text(text = "Enter email")
            },
            onValueChange = {
                userEmail = it
            })
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            value = userPassword,
            placeholder = {
                Text(text = "Enter password")
            },
            onValueChange = {
                userPassword = it
            }
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {
            viewModel.signUp(
                context,
                displayName,
                userEmail,
                userPassword,
                navController = navController,
            )
        }) {
            Text(text = "Sign Up")
        }

        Button(onClick = {
            navController.navigate("login")
        }) {
            Text(text = "Return to login")
        }
    }
}