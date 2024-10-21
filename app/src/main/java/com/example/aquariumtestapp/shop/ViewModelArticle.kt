package com.example.aquariumtestapp.shop

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.data.network.SupabaseClient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ViewModelArticle : ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    private val _articleData =
        MutableStateFlow<List<Article>?>(null) // Utilisation de MutableStateFlow pour gérer l'état des données
    val articleData: StateFlow<List<Article>?> get() = _articleData // Exposition de l'état

    fun getArticle() {
        viewModelScope.launch {
            try {

                _userState.value = UserState.Loading
                val response = SupabaseClient.client.postgrest["Articles"].select()
                val listType = object : TypeToken<List<Article>>() {}.type
                val aquariumList: List<Article> = Gson().fromJson(response.data, listType)
                _articleData.value = aquariumList
                _userState.value = UserState.Success("Json load")
            } catch (e: Exception) {
                _articleData.value = emptyList()
                _userState.value = UserState.Error("Error: ${e.message}")
                println("error" + e.message)
            }
        }
    }
}