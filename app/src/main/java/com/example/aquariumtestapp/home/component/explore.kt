package com.example.aquariumtestapp.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.data.network.SupabaseClient
import io.github.jan.supabase.gotrue.auth

@Composable
fun exploreTask() {

    val user = SupabaseClient.client.auth.currentUserOrNull()
    val metadata = user?.userMetadata
    // Text(text = "Welcome ${metadata?.get("displayname")}")
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.11f)

    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ){
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp),
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_user), // Remplace "nom_de_ton_image" par le nom de ton fichier image sans l'extension
                    contentDescription = null, // Description pour l'accessibilité, si nécessaire
                    modifier = Modifier
                        .fillMaxWidth(0.13f)
                        .clip(shape = CircleShape),
                    contentScale = ContentScale.Fit // Ajuste la mise à l'échelle selon tes besoins
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxSize()
                )
                {

                    Text("Bonjour ${metadata?.get("displayname")?.toString()?.replace("\"", "")} !", fontSize = 17.sp)
                    Text("Notifications", fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                }
            }

            Icon(
                painter = painterResource(id = R.drawable.ic_notif),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = Color.Gray
            )
            
        }



    }
}