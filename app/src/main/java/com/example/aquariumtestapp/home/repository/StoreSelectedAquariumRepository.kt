package com.example.aquariumtestapp.home.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.example.aquariumtestapp.home.viewModel.StoreSelectedAquariumViewModel
import com.example.aquariumtestapp.home.viewModel.StoreSelectedAquariumViewModel.Companion.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreSelectedAquariumRepository { // permet de gerer le stockage de l'aquariums selectionnés dans le cache

    fun getSelectedAquarium(context : Context) : Flow<Int?>
    {
        return context.dataStore.data.map { preferences ->
                preferences[StoreSelectedAquariumViewModel.SELECTED_AQUA_KEY] ?: 1
            }
    }

    fun getSelectedAquariumName(context : Context) : Flow<String>
    {
        return context.dataStore.data.map { preferences ->
                preferences[StoreSelectedAquariumViewModel.SELECTED_AQUA_NAME_KEY] ?: ""
            }
    }

    //save email into datastore
    suspend fun saveSelectedAquarium(id: Int, name: String, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[StoreSelectedAquariumViewModel.SELECTED_AQUA_KEY] = id
            preferences[StoreSelectedAquariumViewModel.SELECTED_AQUA_NAME_KEY] = name
        }
    }

    suspend fun deleteSelectedAquarium(id : Int, context: Context)
    {
        context.dataStore.edit { preferences ->
            val currentId = preferences[StoreSelectedAquariumViewModel.SELECTED_AQUA_KEY]

            // Si l'ID actuel correspond à celui qu'on veut supprimer
            if (currentId == id) {
                preferences.remove(StoreSelectedAquariumViewModel.SELECTED_AQUA_KEY)
                preferences.remove(StoreSelectedAquariumViewModel.SELECTED_AQUA_NAME_KEY)
            }
        }
    }

}