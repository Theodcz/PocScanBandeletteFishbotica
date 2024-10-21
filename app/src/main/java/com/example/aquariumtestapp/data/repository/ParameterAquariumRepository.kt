package com.example.aquariumtestapp.data.repository

import android.util.Log
import com.example.aquariumtestapp.data.model.ParameterAquarium
import com.example.aquariumtestapp.data.model.ParameterAquariumGetBdd
import com.example.aquariumtestapp.data.network.SupabaseClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.jan.supabase.postgrest.postgrest

class ParameterAquariumRepository {


    /*suspend fun postParameterAquarium(parameter: ParameterAquarium) {
        try {
            SupabaseClient.client.postgrest["ParameterAquarium"].insert(
                parameter
            )
            Log.e("kilo","postParameterAquarium success")
        } catch (e: Exception) {
            Log.e("kilo","Error postParameterAquarium : ${e.message}")
        }
    }*/

    suspend fun postParameterAquarium(parameter: ParameterAquarium) {
        try {
            SupabaseClient.client.postgrest["ChemicalParameter"].insert(
                parameter
            )
            Log.e("kilo","postParameterAquarium success")
        } catch (e: Exception) {
            Log.e("kilo","Error postParameterAquarium : ${e.message}")
        }
    }


    suspend fun getParameterAquarium(idAquarium: Int): List<ParameterAquariumGetBdd> {

        try {
            val response = SupabaseClient.client.postgrest["ChemicalParameter"].select {
                filter {
                    eq("aquariumId", idAquarium)
                }
            }
            val listType = object : TypeToken<List<ParameterAquariumGetBdd>>() {}.type
            val parameterList: List<ParameterAquariumGetBdd> = Gson().fromJson(response.data, listType)
            Log.e("kilo"," getParameterAquarium : " + response.data.toString())

            return parameterList.map {
                ParameterAquariumGetBdd(it.aquariumId,it.timestamp, it.CL2, it.GH, it.KH, it.NO2, it.NO3, it.PH, it.TA)
            }

        } catch (e: Exception) {
            Log.e("kilo","error getParameterAquarium : " + e.message)
            return emptyList()
        }
    }
}