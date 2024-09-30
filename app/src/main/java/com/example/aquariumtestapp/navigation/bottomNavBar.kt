package com.example.aquariumtestapp.navigation

import com.example.aquariumtestapp.home.home
import com.example.aquariumtestapp.history.history
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import com.example.aquariumtestapp.R
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquariumtestapp.ui.theme.white
import com.example.aquariumtestapp.ui.theme.gray
import com.example.aquariumtestapp.ui.theme.blue
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember { mutableStateOf("home") }

    Scaffold(
        bottomBar = {
            BottomAppBar (
                containerColor = white
            )
            {
                IconButton(
                    onClick = {
                        selected.value = "home"
                        navigationController.navigate(Screens.HomeScreen.screen)
                        {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally){
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home), contentDescription = null, modifier = Modifier.size(26.dp),
                            tint = if (selected.value == "home") blue else gray)
                        if (selected.value == "home") {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .padding(top = 4.dp)
                                    .background(blue, shape = CircleShape)
                            )
                        }
                        else {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .padding(top = 4.dp)
                                    .background(white, shape = CircleShape)
                            )
                        }
                    }
                }
                IconButton(
                    onClick = {
                        selected.value = "history"
                        navigationController.navigate(Screens.HistoryScreen.screen)
                        {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Default.List,
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == "history") blue else gray
                        )
                        if (selected.value == "history") {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .padding(top = 4.dp)
                                    .background(blue, shape = CircleShape)
                            )
                        } else {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .padding(top = 4.dp)
                                    .background(white, shape = CircleShape)
                            )
                        }
                    }
                }
            }
        }
    )
    {
            paddingValues ->
        NavHost(navController = navigationController,
            startDestination = Screens.HomeScreen.screen,
            modifier = Modifier.padding(paddingValues))
        {
            composable(Screens.HomeScreen.screen) { home() }
            composable(Screens.HistoryScreen.screen) { history() }
        }
    }

}


