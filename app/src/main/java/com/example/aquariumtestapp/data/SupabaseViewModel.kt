package com.example.aquariumtestapp.data


import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.data.network.SupabaseClient.client
import com.example.aquariumtestapp.utils.SharedPreferenceHelper
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class SupabaseViewModel : ViewModel() {
    private val _userState = mutableStateOf<UserState>(UserState.Loading)
    val userState: State<UserState> = _userState

    fun signUp(
        context: Context,
        displayName: String,
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

            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }

        }
    }

    private fun saveToken(context: Context) {
        viewModelScope.launch {
            val accessToken = client.auth.currentAccessTokenOrNull()
            val sharedPref = SharedPreferenceHelper(context)
            sharedPref.saveStringData("accessToken", accessToken)
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

    fun logout(
        context: Context,
        navController: NavController
    ) {
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

                    // Redirige vers la page de connexion
                    navController.navigate("login&register")
                } else {
                    _userState.value = UserState.Error("No session available to logout.")
                }
            } catch (e: Exception) {
                _userState.value = UserState.Error(e.message ?: "")
            }
        }
    }

    fun isUserLoggedIn(context: Context) {
        viewModelScope.launch {
            try {
                _userState.value = UserState.Loading
                val token = getToken(context)
                if (token.isNullOrEmpty()) {
                    _userState.value = UserState.Success("User not logged in!")
                } else {
                    client.auth.retrieveUser(token)
                    client.auth.refreshCurrentSession()
                    saveToken(context)
                    _userState.value = UserState.Success("User already logged in!")
                }
            } catch (e: RestException) {
                _userState.value = UserState.Error(e.error)
            }
        }
    }
}