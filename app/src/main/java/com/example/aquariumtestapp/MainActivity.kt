package com.example.aquariumtestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aquariumtestapp.connect.LoginScreen
import com.example.aquariumtestapp.connect.PageLoginAndConnect
import com.example.aquariumtestapp.connect.Register
import com.example.aquariumtestapp.navigation.bottomAppBar
import com.example.aquariumtestapp.ui.theme.AquariumTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_AquariumTestApp)

        super.onCreate(savedInstanceState)
        setContent {
            AquariumTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavigationComponent(navController = navController)
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "bienvenue") {
        composable("bienvenue") {
            PageBienvenue(navController = navController)
        }

        composable("login&register") {
            PageLoginAndConnect(navController = navController)
        }
        composable("bottomAppBar") {
            bottomAppBar(navController = navController)
        }

        composable("register") {
            Register(navController = navController)
        }

        composable("login") {
            LoginScreen(navController = navController)
        }
        /*composable("listAquarium") {
            listAqua(navController)
        }*/
    }
}

