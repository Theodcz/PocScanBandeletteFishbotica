package com.example.aquariumtestapp.data.repository

import com.example.aquariumtestapp.data.model.AquariumInsert
import com.example.aquariumtestapp.data.model.AquariumSelect
import com.example.aquariumtestapp.data.network.SupabaseClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.postgrest



class SelectAquariumRepository { // permet de gerer la liste des aquariums

    private val user = SupabaseClient.client.auth.currentUserOrNull()
    private val userId = user?.id

    suspend fun getAquariums(): List<AquariumSelect> {

        try {
            val response = SupabaseClient.client.postgrest["Aquarium"].select {
                filter {
                    eq("uuid", userId.toString())
                }
            }
            val listType = object : TypeToken<List<AquariumSelect>>() {}.type
            val aquariumList: List<AquariumSelect> = Gson().fromJson(response.data, listType)
            return aquariumList.map {
                AquariumSelect(it.aquariumId, it.uuid, it.volume, it.name)
            }
        } catch (e: Exception) {
            println("error" + e.message)
            return emptyList()
        }
    }

    suspend fun deleteAquarium(idAquarium: Int) {
        try {
            val response = SupabaseClient.client.postgrest["Aquarium"].delete {
                filter {
                    eq("aquariumId", idAquarium)
                    eq("uuid", userId.toString())
                }
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    suspend fun saveAquarium(name: String, volume: Int) {
        try {
            SupabaseClient.client.postgrest["Aquarium"].insert(
                AquariumInsert(
                    uuid = userId.toString(),
                    name = name,
                    volume = volume,
                ),
            )
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }


}