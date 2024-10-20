package com.example.aquariumtestapp.camera.viewModel

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.camera.repository.CameraRepository
import com.example.aquariumtestapp.data.model.ParameterAquarium
import com.example.aquariumtestapp.data.model.ParameterAquariumGet
import com.example.aquariumtestapp.data.viewModel.ParameterAquariumViewModel
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel : ViewModel() {

    lateinit var outputDirectory: File
    var capturedImageUri: MutableState<Uri?> = mutableStateOf(null)

    private val repository = CameraRepository()

    private val ParameterAquariumViewModel = ParameterAquariumViewModel()

    fun uploadJpgImage(selectedAquariumId: Int, imageUri : Uri) {
        val imagePath = capturedImageUri.value?.path


        fun onResult(success: Boolean, waterTestResults: ParameterAquariumGet?) {
            if (success) {
                //Log.e("kilo", "Upload success, results: $waterTestResults")
                val chemicalParameters = waterTestResults?.let {
                    ParameterAquarium(
                        PH = it.PH,
                        KH = waterTestResults.KH,
                        TA = waterTestResults.TA,
                        GH = waterTestResults.GH,
                        CL2 = waterTestResults.CL2,
                        NO2 = waterTestResults.NO2,
                        NO3 = waterTestResults.NO3,
                        aquariumId = selectedAquariumId,
                    )
                }

                if (chemicalParameters != null) {
                    ParameterAquariumViewModel.postParameterAquarium(chemicalParameters)
                }

            } else {
                Log.e("kilo", "Upload failed")
            }
        }


        val imageUriPath = imageUri.path
        if (imageUriPath != null) {
            val imageFile = File(imageUriPath)
            viewModelScope.launch {
                repository.uploadJpgImage(imageFile, ::onResult)
            }

        }
        else {
            Log.e("kilo", "No image URI found.")
        }

    }



}

