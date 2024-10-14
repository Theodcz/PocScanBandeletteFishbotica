package com.example.aquariumtestapp.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.data.SupabaseViewModel
import com.example.aquariumtestapp.data.network.SupabaseClient
import io.github.jan.supabase.gotrue.auth


@Composable
fun account(viewModel: SupabaseViewModel = viewModel(), navController: NavHostController) {

    val context = LocalContext.current

    val user = SupabaseClient.client.auth.currentUserOrNull()
    val metadata = user?.userMetadata

    // 1er colonne image de fond
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Centre horizontalement
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize(),
    )
    {
        Image(
            painter = painterResource(id = R.drawable.fondpoisson),
            contentDescription = "fond poissons",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
        )
    }

    // 2eme colonne image de profil + informations
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, // Centre horizontalement
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 85.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_user), // Remplace "nom_de_ton_image" par le nom de ton fichier image sans l'extension
            contentDescription = null, // Description pour l'accessibilité, si nécessaire
            modifier = Modifier
                .fillMaxWidth(0.13f)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Fit // Ajuste la mise à l'échelle selon tes besoins
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            "${metadata?.get("displayname")?.toString()?.replace("\"", "")}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.padding(5.dp))

        Text(
            "${metadata?.get("email")?.toString()?.replace("\"", "")}",
            fontSize = 12.sp,
            color = Color.White
        )
    }

    // 3eme colonne bouton logout
    Column(

        horizontalAlignment = Alignment.CenterHorizontally, // Centre horizontalement
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 230.dp),
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp, 600.dp)// Ajuste la taille selon tes besoins
                .background(
                    colorResource(R.color.whiteBackground),
                    shape = RoundedCornerShape(16.dp)
                ) // Style de la Box
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0B7FB5)),
                    onClick = {
                        viewModel.logout(context, navController = navController)
                    }) {
                    Text(text = "Se déconnecter")
                }
            }

        }
    }
}
