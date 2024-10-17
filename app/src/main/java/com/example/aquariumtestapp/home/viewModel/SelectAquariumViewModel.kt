package com.example.aquariumtestapp.home.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.data.model.AquariumSelect
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.data.network.SupabaseClient
import com.example.aquariumtestapp.home.repository.SelectAquariumRepository
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.launch

class SelectAquariumViewModel : ViewModel() { // permet de gerer la liste des aquariums


    // Gestion BDD supabase

    // Global variable
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState
    private val repository = SelectAquariumRepository()


    // Récupération des aquariums
    private val _aquariumData = mutableStateOf<List<AquariumSelect>>(emptyList())
    val aquariumData: State<List<AquariumSelect>> = _aquariumData

    fun getAquarium() {

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                _aquariumData.value = repository.getAquariums()

                _userState.value = UserState.Success("Json load")
            } catch (e: Exception) {
                _aquariumData.value = emptyList()
                try {

                } catch (e: Exception) {
                    _aquariumData.value = emptyList()
                }
                _userState.value = UserState.Error("Error: ${e.message}")
                println("error" + e.message)
            }
        }
    }

    // Suppression de l'aquarium

    fun deleteAquarium(idAquarium: Int) {
        val user = SupabaseClient.client.auth.currentUserOrNull()
        val userId = user?.id

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading

                repository.deleteAquarium(idAquarium)

                getAquarium()
                _userState.value = UserState.Success("Deleted Aquarium successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
                println("Error: ${e.message}")
            }
        }

    }

    fun saveAquarium(name: String, volume: Int) {
        val user = SupabaseClient.client.auth.currentUserOrNull()
        val id = user?.id

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                repository.saveAquarium(name, volume)
                _userState.value = UserState.Success("Added Aquarium successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }


}