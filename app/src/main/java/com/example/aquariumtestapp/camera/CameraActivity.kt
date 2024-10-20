package com.example.aquariumtestapp.camera

import CapturedImageView
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.camera.viewModel.CameraViewModel
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : ComponentActivity() {

    private val cameraViewModel = CameraViewModel()
    private lateinit var cameraExecutor: ExecutorService
    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher =
        registerForActivityResult( // vérifie si la permissition de la caméra à déja été accordé
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                Log.i("kilo", "Permission granted")
                shouldShowCamera.value = true
            } else {
                Log.i("kilo", "Permission denied")
            }
        }

    private fun requestCameraPermission() { // demande la permission de la caméra
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) -> {
                Log.i("kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }


    private fun openAppSettings() {
        finish()
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) { // se lance lorsqu'on lance l'activité
        super.onCreate(savedInstanceState)
        cameraViewModel.outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
        requestCameraPermission()
        setContent {
            if (shouldShowCamera.value) { // si la permission de la caméra est accordé alors on affiche la caméra
                CameraView(
                    outputDirectory = cameraViewModel.outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = { uri ->
                        handleImageCapture(uri) // Envoie l'URI de l'image

                        cameraViewModel.capturedImageUri.value = uri // Mettez à jour l'URI capturé

                        /*if (selectedAquarium != null) {
                            cameraViewModel.uploadJpgImage(selectedAquarium.toInt())
                        }
                        else {
                            cameraViewModel.uploadJpgImage(18)
                            Log.e("kilo", "No selected aquarium found. Defaulting to 1")
                        }*/

                    },
                    onError = { Log.e("kilo", "View error:", it) },
                    onBackPressed = {
                        finish() // Fermer l'activité ici
                    }
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    IconButton(
                        onClick = {
                            finish() // ferme l'activité
                        },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally, // Centre le contenu horizontalement
                        verticalArrangement = Arrangement.spacedBy(20.dp) // Espace entre les éléments
                    ) {
                        Text(
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            text = "Waiting for camera permission...\n\nPlease grant camera permission to continue."
                        )
                        Button(
                            onClick = { openAppSettings() },
                        ) {
                            Text("Open Settings")
                        }
                    }


                }
            }
            cameraViewModel.capturedImageUri.value?.let { uri ->
                CapturedImageView(uri,
                    onResult = { isAccepted ->
                        if (isAccepted) {
                            Toast.makeText(this, "Image validated!", Toast.LENGTH_SHORT).show()
                        } else {
                            cameraViewModel.capturedImageUri.value = null // Réinitialise l'URI
                        }
                    },
                    onValidPost = {
                        cameraViewModel.capturedImageUri.value = null
                        finish()
                })
            }
        }
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}





