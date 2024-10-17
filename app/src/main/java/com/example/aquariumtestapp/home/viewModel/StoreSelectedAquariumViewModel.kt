package com.example.aquariumtestapp.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreSelectedAquarium (private val context: Context){

    companion object {
        private val Context.dataStoree: DataStore<Preferences> by preferencesDataStore("SelectedAquarium")
        val SELECTED_AQUA_KEY = intPreferencesKey("selected_aquarium")
    }

    //get the saved email
    val getEmail: Flow<Int?> = context.dataStoree.data
        .map { preferences ->
            preferences[SELECTED_AQUA_KEY] ?: 1
        }

    //save email into datastore
    suspend fun saveEmail(name: Int) {
        context.dataStoree.edit { preferences ->
            preferences[SELECTED_AQUA_KEY] = name
        }
    }
}