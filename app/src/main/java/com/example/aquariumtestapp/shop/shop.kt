package com.example.aquariumtestapp.shop

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aquariumtestapp.R

@Composable
fun shop () {

    val context = LocalContext.current

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text("Bandelette de test")

            Image(
                painter = painterResource(id = R.drawable.aquatest),
                contentDescription = "plaquette", modifier = Modifier.size(250.dp).padding(16.dp).clickable {
                    // Créer une intention pour ouvrir un lien dans un navigateur
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://deploy-app-fishbotica.vercel.app/boutique"))
                    context.startActivity(intent)
                }
            )
        }
    }
}