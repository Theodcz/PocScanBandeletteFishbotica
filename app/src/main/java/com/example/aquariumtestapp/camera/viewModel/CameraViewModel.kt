package com.example.aquariumtestapp.camera.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.camera.repository.CameraRepository
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel : ViewModel() {

    lateinit var outputDirectory: File
    var capturedImageUri: MutableState<Uri?> = mutableStateOf(null)

    private val repository = CameraRepository()


    /*
    fun uploadImage(file: File, onResult: (Boolean, String?) -> Unit) {
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", /*file.name*/
            file.name, requestFile)

        repository.uploadImage(body) { success, message ->
            onResult(success, message)
        }
    }*/

    fun uploadJpgImage() {
        val imagePath = capturedImageUri.value?.path

        if (imagePath != null) {
            val imageFile = File(imagePath)
            viewModelScope.launch {
                repository.uploadJpgImage(imageFile)
            }

        }
        else {
            Log.e("kilo", "No image URI found.")
        }
    }



}

