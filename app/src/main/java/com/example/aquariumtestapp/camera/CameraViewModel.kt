package com.example.aquariumtestapp.camera

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import java.io.File

class CameraViewModel : ViewModel() {

    lateinit var outputDirectory: File
    var capturedImageUri: MutableState<Uri?> = mutableStateOf(null)






}

