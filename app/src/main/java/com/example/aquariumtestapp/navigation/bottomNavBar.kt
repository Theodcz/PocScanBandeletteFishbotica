package com.example.aquariumtestapp.navigation

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquariumtestapp.DataViewModel
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.account.account
import com.example.aquariumtestapp.camera.CameraActivity
import com.example.aquariumtestapp.history.history
import com.example.aquariumtestapp.home.home
import com.example.aquariumtestapp.shop.shop
import com.example.aquariumtestapp.ui.theme.blue
import com.example.aquariumtestapp.ui.theme.gray
import com.example.aquariumtestapp.ui.theme.white
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current
    val selected = remember { mutableStateOf("home") }
    val dataViewModel : DataViewModel = viewModel();
    Scaffold(
        bottomBar = {
            BottomAppBar(
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
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_home),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == "home") blue else gray
                        )
                        if (selected.value == "home") {
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
                IconButton(
                    onClick = {
                        selected.value = "history"
                        navigationController.navigate(Screens.HistoryScreen.screen)
                        {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_history),
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
                IconButton(
                    onClick = {
                        selected.value = "shop"
                        navigationController.navigate(Screens.ShopScreen.screen)
                        {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_shop),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp),
                            tint = if (selected.value == "shop") blue else gray
                        )
                        if (selected.value == "shop") {
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
                IconButton(
                    onClick = {
                        selected.value = "account"
                        navigationController.navigate(Screens.AccountScreen.screen)
                        {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_account),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = if (selected.value == "account") blue else gray
                        )
                        if (selected.value == "account") {
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
        },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton =  {

            androidx.compose.material3.FloatingActionButton(
                shape = CircleShape,
                onClick = {
                    context.startActivity(Intent(context, CameraActivity::class.java))
                },
                containerColor = white, // Couleur de ton choix
                modifier = Modifier.offset(x=0.dp, y=50.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_scan),
                    contentDescription = "Open Camera",
                    modifier = Modifier.size(24.dp),
                    tint = gray
                )
            }
        }
    )
    { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.HomeScreen.screen,
            modifier = Modifier.padding(paddingValues))
        {
            composable(Screens.HomeScreen.screen) { home(dataViewModel) }
            composable(Screens.HistoryScreen.screen) { history() }
            composable(Screens.ShopScreen.screen) { shop() }
            composable(Screens.AccountScreen.screen) { account() }
        }
    }
}


