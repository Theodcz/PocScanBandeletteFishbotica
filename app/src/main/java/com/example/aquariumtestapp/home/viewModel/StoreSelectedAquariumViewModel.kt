package com.example.aquariumtestapp.home.viewModel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.home.repository.StoreSelectedAquariumRepository
import kotlinx.coroutines.launch

class StoreSelectedAquariumViewModel (private val context: Context) : ViewModel() { // permet de gerer le stockage de l'aquariums selectionnés dans le cache

    private val repository = StoreSelectedAquariumRepository()
    var aquariumName = mutableStateOf("")

    var selectedAquarium = mutableStateOf<Int?>(null)//

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("SelectedAquarium")
        val SELECTED_AQUA_KEY = intPreferencesKey("selected_aquariumId")
        val SELECTED_AQUA_NAME_KEY = stringPreferencesKey("selected_aquariumName")
    }

    fun getSelectedAquariumId() {

        viewModelScope.launch {

            repository.getSelectedAquarium(context).collect { aquariumId ->
                selectedAquarium.value = aquariumId

            }
        }
    }
    fun getSelectedAquariumName() {

        viewModelScope.launch {

            repository.getSelectedAquariumName(context).collect { _aquariumName ->

                aquariumName.value = _aquariumName

            }

        }
    }

    fun saveSelectedAquarium(id: Int, name: String) {
        viewModelScope.launch {
            repository.saveSelectedAquarium(id, name, context)

        }
    }

    fun deleteSelectedAquarium(id: Int) {
        viewModelScope.launch {
            repository.deleteSelectedAquarium(id, context)
        }
    }
}