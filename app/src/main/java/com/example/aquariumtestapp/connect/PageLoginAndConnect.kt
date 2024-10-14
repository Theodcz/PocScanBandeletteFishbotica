package com.example.aquariumtestapp.connect

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.aquariumtestapp.R

@Composable
fun PageLoginAndConnect(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 197.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centre horizontalement
    ) {
        Image(
            painter = painterResource(id = R.drawable.logoconnexion),
            contentDescription = "LogoConnexion",
            modifier = Modifier.size(211.dp, 168.dp)

        )

        Row(
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(13.dp) // Taille du cercle (diamètre)
                    .background(
                        color = Color(0xFFE7F3F8),
                        shape = CircleShape
                    ) // Couleur et forme
            )

            Spacer(modifier = Modifier.padding(5.dp))

            Box(
                modifier = Modifier
                    .size(13.dp) // Taille du cercle (diamètre)
                    .background(
                        color = Color(0xFF0B7FB5),
                        shape = CircleShape
                    ) // Couleur et forme
            )
        }

        // Text semi bold 18 sp
        Text(
            text = "Connectons - nous !",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(top = 40.dp)
        )

        Text(
            text = " Pour profiter d'une expérience optimale, " +
                    "connecte-toi au service Fishbotica afin d'obtenir toutes " +
                    "les fonctionnalités pour ton aquarium.",
            fontSize = 13.sp,
            modifier = Modifier
                .padding(top = 15.dp, start = 90.dp, end = 90.dp)
                .fillMaxWidth(), // Prend toute la largeur de la page
            textAlign = TextAlign.Center,
            color = Color.Black.copy(alpha = 0.74f)
        )

        Row(
            modifier = Modifier.padding(top = 50.dp)

        ) {
            Button(colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B7FB5).copy(alpha = 0.1f), // Couleur de fond personnalisée
                contentColor = Color(0xFF0B7FB5) // Couleur du texte dans le bouton
            ),
                modifier = Modifier.size(140.dp, 48.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("register")
                }) {
                Text(
                    text = "Créez un compte",
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.padding(start = 20.dp))

            Button(colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B7FB5), // Couleur de fond personnalisée
                contentColor = Color.White // Couleur du texte dans le bouton
            ),
                modifier = Modifier.size(140.dp, 48.dp),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    navController.navigate("login")
                }) {
                Text(
                    text = "Se connecter",
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

