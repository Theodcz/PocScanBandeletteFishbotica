package com.example.aquariumtestapp.camera.repository

import android.util.Log
import com.example.aquariumtestapp.camera.retrofit.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File


class CameraRepository {
/*
    fun uploadImage(image: MultipartBody.Part, onResult: (Boolean, String?) -> Unit) {
        RetrofitInstance.api.uploadImage(image).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e("kilo", "Upload response: ${response.body().toString()}")
                if (response.isSuccessful) {
                    Log.e("kilo", "Image uploaded successfully")
                    onResult(true, response.body()?.string())
                } else {
                    val errorMessage = response.errorBody()?.string()
                    Log.e("CameraRepository", " upload failed: $errorMessage")
                    onResult(false, response.errorBody()?.string())
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("kilo", "Upload error: ${t.message}")
                onResult(false, t.message)
            }
        })
    }*/

    suspend fun uploadJpgImage(file: File) {
        if (!file.exists()) {
            throw IllegalArgumentException("File does not exist!")
        }
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

        // Create a request body with the file and the image/jpeg media type
        val requestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())

        // Create a MultipartBody.Part using the file and its name
        val multipartBody = MultipartBody.Part.createFormData(
            "image",
            file.name,
            requestBody
        )

        // Upload the image via the API service
        val response = RetrofitInstance.apiService.uploadImage(imagePart)
        if (response.isSuccessful && response.body()?.success == true) {
            // Image upload successful, handle the response
            val imageUrl = response.body()?.imageUrl
            Log.e("CameraRepository", "Image uploaded successfully: $imageUrl")
            // Do something with imageUrl
        } else {
            // Image upload failed, handle the error
            val errorMessage = response.errorBody()?.string()
            Log.e("CameraRepository", "Image upload failed: $errorMessage")
            // Handle the error
        }
    }
}