package com.example.aquariumtestapp


import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aquariumtestapp.data.model.AquariumInsert
import com.example.aquariumtestapp.data.model.AquariumSelect
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.data.network.SupabaseClient.client
import com.example.aquariumtestapp.utils.SharedPreferenceHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseViewModel : ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun signUp(
        context: Context,
        displayName : String,
        userEmail: String,
        userPassword: String,
        navController: NavController,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.auth.signUpWith(Email) {
                    email = userEmail
                    password = userPassword
                    data = buildJsonObject {
                        put("displayname", displayName)
                    }
                }
                saveToken(context)
                _userState.value = UserState.Success("Registered successfully!")
                navController.navigate("bottomAppBar")

            } catch(e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = client.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken",accessToken)
        }
    }

    private fun getToken(context: Context): String? {
        val sharedPref = SharedPreferenceHelper(context)
        return sharedPref.getStringData("accessToken")
    }

    fun login(
        context: Context,
        userEmail: String,
        userPassword: String,
        navController: NavController,
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.auth.signInWith(Email) {
                    email = userEmail
                    password = userPassword
                }
                saveToken(context)
                _userState.value = UserState.Success("Logged in successfully!")
                navController.navigate("bottomAppBar")

            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    fun logout(context: Context) {
        val sharedPref = SharedPreferenceHelper(context)
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading

                // Vérifie si une session est disponible
                val session = client.auth.currentSessionOrNull()
                if (session != null) {
                    client.auth.signOut()
                    sharedPref.clearPreferences()
                    _userState.value = UserState.Success("Logged out successfully!")

                    // Redirection vers MainActivity après logout
                    val intent = Intent(context, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    context.startActivity(intent)

                    // Finir l'activité actuelle si c'est une ComponentActivity
                    if (context is ComponentActivity) {
                        context.finish() // Termine l'activité actuelle
                    }
                } else {
                    _userState.value = UserState.Error("No session available to logout.")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    fun isUserLoggedIn(
        context: Context,
        navController: NavController
    ) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val token = getToken(context)
                if(token.isNullOrEmpty()) {
                    _userState.value = UserState.Success("User not logged in!")
                } else {
                    client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User already logged in!")
                    navController.navigate("bottomAppBar")
                }
            } catch (e: RestException) {
                _userState.value = UserState.Error(e.error)
            }
        }
    }

    private val _aquariumData = MutableStateFlow<List<AquariumSelect>?>(null) // Utilisation de MutableStateFlow pour gérer l'état des données
    val aquariumData: StateFlow<List<AquariumSelect>?> get() = _aquariumData // Exposition de l'état


    fun getAquarium() {
        val user = client.auth.currentUserOrNull()
        val id = user?.id

        viewModelScope.launch {
            try {

                _userState.value = UserState.Loading
                val response = client.postgrest["Aquarium"].select {
                    filter {
                        eq("uuid", id.toString())
                    }
                }
                val listType = object : TypeToken<List<AquariumSelect>>() {}.type
                val aquariumList: List<AquariumSelect> = Gson().fromJson(response.data, listType)
                _aquariumData.value = aquariumList
                _userState.value = UserState.Success("Json load")
            } catch (e: Exception) {
                _aquariumData.value = emptyList()
                _userState.value = UserState.Error("Error: ${e.message}")
                println("error" + e.message)
            }
        }
    }

    fun deleteAquarium(idAquarium : Int) {
        val user = client.auth.currentUserOrNull()
        val userId = user?.id

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading

                // Effectuer la requête pour supprimer l'aquarium
                val response = client.postgrest["Aquarium"].delete {
                    filter{
                        eq("aquariumId", idAquarium)
                        eq("uuid", userId.toString())
                    }
                }
                getAquarium()
                println(response)
                _userState.value = UserState.Success("Deleted Aquarium successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
                println("Error: ${e.message}")
            }
        }

    }

    fun saveAquarium(name : String, volume : Int) {
        val user = client.auth.currentUserOrNull()
        val id = user?.id

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.postgrest["Aquarium"].insert(
                    AquariumInsert(
                        uuid = id.toString(),
                        name = name,
                        volume = volume,
                    ),
                )
                _userState.value = UserState.Success("Added Aquarium successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }
}