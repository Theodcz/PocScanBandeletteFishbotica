package com.example.aquariumtestapp.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel


class HomeViewModel : ViewModel() {

    val stateSheet = mutableStateOf(false)

    fun changeState() {
        stateSheet.value = !stateSheet.value
    }
}