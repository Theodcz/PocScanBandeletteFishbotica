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
import com.example.aquariumtestapp.data.network.SupabaseClient
import io.github.jan.supabase.gotrue.gotrue

@Composable
fun home () {
    val context = LocalContext.current

    val user = SupabaseClient.client.gotrue.currentUserOrNull()
    val metadata = user?.userMetadata

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {



        Text(text = "Welcome ${metadata?.get("displayname")}")
    }
}