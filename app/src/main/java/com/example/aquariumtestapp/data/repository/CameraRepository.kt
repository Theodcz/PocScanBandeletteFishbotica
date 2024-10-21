package com.example.aquariumtestapp.data.repository

import android.util.Log
import com.example.aquariumtestapp.data.model.ParameterAquariumGet
import com.example.aquariumtestapp.data.retrofit.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File


class CameraRepository { // permet de gerer l'upload des images à l'api

    suspend fun uploadJpgImage(file: File, onResult: (Boolean, ParameterAquariumGet?) -> Unit) {
        if (!file.exists()) {
            throw IllegalArgumentException("File does not exist!")
        }
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        try {
            val response = RetrofitInstance.apiService.uploadImage(imagePart)

            if (response.isSuccessful && response.body() != null) {
                val waterTestResults = response.body()!!
                Log.e("kilo", "Upload success, results: $waterTestResults")
                onResult(true, waterTestResults)
            } else {
                Log.e("kilo", "Upload failed: ${response.errorBody()?.string()}")
                onResult(false, null)
            }
        } catch (e: HttpException) {
            Log.e("kilo", "HttpException: ${e.message}")
            onResult(false, null)
        } catch (e: Exception) {
            Log.e("kilo", "Error: ${e.message}")
            onResult(false, null)
        }
    }
}