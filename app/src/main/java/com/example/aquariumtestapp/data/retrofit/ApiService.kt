package com.example.aquariumtestapp.data.retrofit

import com.example.aquariumtestapp.data.model.ParameterAquariumGet
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @Multipart
    @POST("upload_image")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<ParameterAquariumGet>
}