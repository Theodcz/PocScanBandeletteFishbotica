package com.example.aquariumtestapp.data.model

sealed class UserState {
    object Loading: UserState()

    object Idle : UserState()

    data class Success(val message: String): UserState()
    data class Error(val message: String): UserState()
}
