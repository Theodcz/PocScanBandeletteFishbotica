package com.example.aquariumtestapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataViewModel: ViewModel() {
    private val _aquariumSelectedId = MutableStateFlow<Int>(0)
    val aquariumSelected: StateFlow<Int> get() = _aquariumSelectedId // Exposition de l'état

    private val _aquariumName = MutableStateFlow<String>("")
    val aquariumName: StateFlow<String> get() = _aquariumName
    fun setAquariumSelected(idAquarium : Int, nameAquarium : String) {
        _aquariumSelectedId.value = idAquarium
        _aquariumName.value = nameAquarium
    }




}