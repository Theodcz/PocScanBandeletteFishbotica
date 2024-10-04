package com.example.aquariumtestapp.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.aquariumtestapp.R


@Composable
fun Home () {
    val context = LocalContext.current
    BlocHautStart()
}

@Composable
fun BlocHautStart() {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        CircularImage()
        Column {
            Text(text = "Bonjour Jamie", modifier = Modifier.padding(8.dp))
            Text(text = "Explore Task", modifier = Modifier.padding(8.dp))
        }

        CircularImage()
    }

}


@Composable
fun CircularImage() {

    Image(
        painter = rememberAsyncImagePainter(R.drawable.test),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp) // Dimension de l'image
            .clip(CircleShape) // Appliquer une forme circulaire
            .border(1.dp, androidx.compose.ui.graphics.Color.Gray, CircleShape) // Ajouter une bordure
    )
}
