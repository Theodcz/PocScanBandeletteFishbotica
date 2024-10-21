package com.example.aquariumtestapp.ui.connect

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.data.viewModel.SupabaseViewModel
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.utils.LoadingComponent
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Register(
    viewModel: SupabaseViewModel = viewModel(),
    navController: androidx.navigation.NavHostController,
) {

    val context = LocalContext.current

    var displayName by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    val userState by viewModel.userState
    var currentUserState by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.clearUserState()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally // Centre horizontalement
    ) {

        Text(
            text = "Créez votre compte",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            modifier = Modifier.padding(top = 50.dp) // Padding autour du texte
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            text = "Inscrivez - vous pour une expérience optimale !",
            fontSize = 13.sp,
        )

        TextField(
            modifier = Modifier
                .padding(top = 24.dp)
                .border(1.dp, Color(0xFFE2EDF2), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // Rend le fond transparent
                unfocusedContainerColor = Color.Transparent, // Rend le fond transparent
                focusedIndicatorColor = Color.Transparent,  // Supprime la ligne lors du focus
                unfocusedIndicatorColor = Color.Transparent // Supprime la ligne sans focus
            ),
            value = displayName,
            placeholder = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Comment dois - je vous appelez ?",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 3.dp) // Padding à gauche
                    )
                }
            }, onValueChange = {
                displayName = it
            })

        Spacer(modifier = Modifier.padding(8.dp))

        TextField(
            modifier = Modifier.border(1.dp, Color(0xFFE2EDF2), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // Rend le fond transparent
                unfocusedContainerColor = Color.Transparent, // Rend le fond transparent
                focusedIndicatorColor = Color.Transparent,  // Supprime la ligne lors du focus
                unfocusedIndicatorColor = Color.Transparent// Supprime la ligne sans focus
            ),
            value = userEmail,
            placeholder = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_alternate_email_24),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Un email pour vous connecter ...",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 3.dp) // Padding à gauche
                    )
                }
            },
            onValueChange = {
                userEmail = it
            })
        Spacer(modifier = Modifier.padding(8.dp))
        TextField(
            modifier = Modifier.border(1.dp, Color(0xFFE2EDF2), shape = RoundedCornerShape(8.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent, // Rend le fond transparent
                unfocusedContainerColor = Color.Transparent, // Rend le fond transparent
                focusedIndicatorColor = Color.Transparent,  // Supprime la ligne lors du focus
                unfocusedIndicatorColor = Color.Transparent // Supprime la ligne sans focus
            ),
            value = userPassword,
            placeholder = {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.lock_24px),
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = "Entrez votre mot de passe ...",
                        fontSize = 13.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 3.dp) // Padding à gauche
                    )
                }
            },
            onValueChange = {
                userPassword = it
            }
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0B7FB5), // Couleur de fond personnalisée
                contentColor = Color.White // Couleur du texte dans le bouton
            ),
            modifier = Modifier.size(204.dp, 48.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                viewModel.signUp(
                    context,
                    displayName,
                    userEmail,
                    userPassword,
                    navController = navController,
                )
            }) {
            Text(text = "Continuer")
        }

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, // Couleur de fond personnalisée
                contentColor = Color.Black // Couleur du texte dans le bouton
            ),
            onClick = {
                navController.navigate("login&register")
            }) {
            Text(text = "Retour", fontSize = 13.sp)
        }

        when (userState) {
            is UserState.Loading ->
                LoadingComponent()

            is UserState.Success -> {
                val message = (userState as UserState.Success).message
                currentUserState = message
            }

            is UserState.Error -> {
                val message = (userState as UserState.Error).message
                currentUserState = message
            }

            else -> {}
        }

        if (currentUserState.isNotEmpty() && userState is UserState.Error) {
            val text = currentUserState
            Text(
                text = "Erreur : $text",
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 13.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}