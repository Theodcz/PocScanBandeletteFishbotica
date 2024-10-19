package com.example.aquariumtestapp.camera.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://127.0.0.1:8000")//"https://api-python-bandelette.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
*/
object RetrofitInstance {

    private const val BASE_URL = "https://api-python-bandelette.onrender.com"//"http://127.0.0.1:8000/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}