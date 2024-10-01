package com.example.aquariumtestapp.home

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.aquariumtestapp.camera.CameraActivity

@Composable
fun home () {
    val context = LocalContext.current


    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        /*Button(onClick = {
            // Lancer CameraActivity
            context.startActivity(Intent(context, CameraActivity::class.java))
        }) {
            Text("Open Camera")
        }*/
        Text(text = "home")
    }
}