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
import androidx.navigation.NavHostController
import com.example.aquariumtestapp.LoadingComponent
import com.example.aquariumtestapp.SupabaseAuthViewModel
import com.example.aquariumtestapp.data.model.UserState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: SupabaseAuthViewModel = viewModel(),
    navController: NavHostController,
) {
    val context = LocalContext.current
    val userState by viewModel.userState

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    var currentUserState by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(
            context,
            navController = navController,
        )
    }

    if(currentUserState == "User not logged in!") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
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
                viewModel.login(
                    context,
                    userEmail,
                    userPassword,
                    navController = navController,
                )
            }) {
                Text(text = "Login")
            }

            Button(onClick = {
                navController.navigate("register")
            }) {
                Text(text = "Inscrivez - vous")
            }

            when (userState) {
                is UserState.Loading -> {
                    LoadingComponent()
                }

                is UserState.Success -> {
                    val message = (userState as UserState.Success).message
                    currentUserState = message
                }

                is UserState.Error -> {
                    val message = (userState as UserState.Error).message
                    currentUserState = message
                }
            }
        }
    } else {
        when (userState) {
            is UserState.Loading -> {
                LoadingComponent()
            }

            is UserState.Success -> {
                val message = (userState as UserState.Success).message
                currentUserState = message
            }

            is UserState.Error -> {
                val message = (userState as UserState.Error).message
                currentUserState = message
            }
        }

        if (currentUserState.isNotEmpty() && userState is UserState.Error) {
            Text(text = currentUserState)
        }

        LoadingComponent()
    }
}