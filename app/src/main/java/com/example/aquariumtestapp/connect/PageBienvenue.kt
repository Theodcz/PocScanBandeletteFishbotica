package com.example.aquariumtestapp.connect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.utils.LoadingComponent

@Composable 
fun PageBienvenue(viewModel: SupabaseViewModel = viewModel(), navController: NavHostController) {
    val context = LocalContext.current
    val userState by viewModel.userState
    var isLoading by remember { mutableStateOf(true) }
    var hasNavigated by remember { mutableStateOf(false) } // Variable pour suivre la navigation

    LaunchedEffect(Unit) {
        viewModel.isUserLoggedIn(context)
    }

    when (userState) {
        is UserState.Loading -> {
            LoadingComponent()
        }

        is UserState.Success -> {
            val message = (userState as UserState.Success).message
            if (message == "User already logged in!" && !hasNavigated) {
                println("Navigating to bottomAppBar")
                hasNavigated = true // Met à jour la variable pour éviter des navigations répétées
                navController.navigate("bottomAppBar")
            } else {
                isLoading = false // L'utilisateur n'est pas connecté, on arrête le chargement
            }
        }

        is UserState.Error -> {
            Text("Erreur : ${(userState as UserState.Error).message}")
        }
    }

    if (!isLoading && !hasNavigated) {


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.aquarium),
                contentDescription = "Aquarium",
                modifier = Modifier
                    .size(272.dp, 260.dp)
            )
            Row(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .size(13.dp) // Taille du cercle (diamètre)
                        .background(
                            color = Color(0xFF0B7FB5),
                            shape = CircleShape
                        ) // Couleur et forme
                )

                Box(
                    modifier = Modifier
                        .size(13.dp) // Taille du cercle (diamètre)
                        .background(
                            color = Color(0xFFE7F3F8),
                            shape = CircleShape
                        ) // Couleur et forme
                )
            }
            Text(
                text = "Bienvenue chez FISHBOTICA",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )


            Text(
                text = "Explorez une expérience unique de gestion, " +
                        "conseils et automatisation " +
                        "pour simplifier votre aventure d'aquariophile.",
                fontSize = 13.sp,
                modifier = Modifier
                    .padding(top = 15.dp, start = 55.dp, end = 55.dp)
                    .fillMaxWidth(), // Prend toute la largeur de la page
                textAlign = TextAlign.Center,
                color = Color.Black.copy(alpha = 0.74f),
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.padding(20.dp))

            Button(colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B7FB5), // Couleur de fond personnalisée
                contentColor = Color.White // Couleur du texte dans le bouton
            ),
                modifier = Modifier.size(303.dp, 48.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("login&register")
                }) {
                Text(
                    text = "Découvrir",
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}