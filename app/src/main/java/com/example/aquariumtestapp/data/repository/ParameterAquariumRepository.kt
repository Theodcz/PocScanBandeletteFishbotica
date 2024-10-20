package com.example.aquariumtestapp.camera.repository

import android.util.Log
import com.example.aquariumtestapp.data.model.ParameterAquarium
import com.example.aquariumtestapp.data.network.SupabaseClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.jan.supabase.postgrest.postgrest

class ParameterAquariumRepository {


    suspend fun postParameterAquarium(parameter: ParameterAquarium) {
        try {
            SupabaseClient.client.postgrest["ParameterAquarium"].insert(
                parameter
            )
            Log.e("kilo","postParameterAquarium success")
        } catch (e: Exception) {
            Log.e("kilo","Error postParameterAquarium : ${e.message}")
        }
    }


    suspend fun getParameterAquarium(idAquarium: Int): List<ParameterAquarium> {

        try {
            val response = SupabaseClient.client.postgrest["ParameterAquarium"].select {
                filter {
                    eq("aquariumId", idAquarium)
                }
            }
            val listType = object : TypeToken<List<ParameterAquarium>>() {}.type
            val parameterList: List<ParameterAquarium> = Gson().fromJson(response.data, listType)
            return parameterList.map {
                ParameterAquarium(it.aquariumId, it.CL2, it.GH, it.KH, it.NO2, it.NO3, it.PH, it.TA)
            }
            Log.e("kilo","error getParameterAquarium : " + response.data.toString())

        } catch (e: Exception) {
            Log.e("kilo","error getParameterAquarium : " + e.message)
            return emptyList()
        }
    }
}