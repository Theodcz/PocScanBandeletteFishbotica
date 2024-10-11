package com.example.aquariumtestapp


import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.data.network.SupabaseClient.client
import com.example.aquariumtestapp.utils.SharedPreferenceHelper
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.gotrue
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import io.github.jan.supabase.gotrue.providers.AuthProvider

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
                client.gotrue.signUpWith(Email) {
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
            val accessToken = client.gotrue.currentAccessTokenOrNull()
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
                client.gotrue.loginWith(Email) {
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
                val session = client.gotrue.currentSessionOrNull()
                if (session != null) {
                    client.gotrue.logout()
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
                    client.gotrue.retrieveUser(token)
                    client.gotrue.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User already logged in!")
                    navController.navigate("bottomAppBar")
                }
            } catch (e: RestException) {
                _userState.value = UserState.Error(e.error)
            }
        }
    }

/*
    fun saveAquarium(name : String, volume : Int) {
        val user = SupabaseClient.client.gotrue.currentUserOrNull()
        val metadata = user?.userMetadata

        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                client.postgrest["Aquarium"].insert(
                    Aquarium(
                        uuid = metadata?.get("uuid").toString().toString(),
                        name = name,
                        volume = volume,
                    ),
                )
                _userState.value = UserState.Success("Added note successfully!")
            } catch (e: Exception) {
                _userState.value = UserState.Error("Error: ${e.message}")
            }
        }
    }*/
}