package com.example.aquariumtestapp.data.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.data.model.ParameterAquarium
import com.example.aquariumtestapp.data.model.ParameterAquariumGetBdd
import com.example.aquariumtestapp.data.repository.ParameterAquariumRepository
import kotlinx.coroutines.launch
class ParameterAquariumViewModel : ViewModel() { // permet de gerer les parametres de l'aquarium

    private val repository = ParameterAquariumRepository()
    private val _parameterData = mutableStateOf<List<ParameterAquariumGetBdd>>(emptyList())
    val parameterData: State<List<ParameterAquariumGetBdd>> get() = _parameterData

    private val _lastParameter = mutableStateOf<ParameterAquariumGetBdd?>(null)
    val lastParameter: State<ParameterAquariumGetBdd?> get() = _lastParameter

    fun postParameterAquarium(parameter: ParameterAquarium, onValidPost: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repository.postParameterAquarium(parameter)
                onValidPost(true)
            } catch (e: Exception) {
                Log.e("kilo","Error postParameterAquarium view model : ${e.message}")
            }


        }
    }



    fun getParameterAquarium(idAquarium : Int) {

        viewModelScope.launch {
            try {
                _parameterData.value = repository.getParameterAquarium(idAquarium)
                val sortedParameters = _parameterData.value.sortedByDescending { it.timestamp }
                _lastParameter.value = sortedParameters.first()
                Log.e("kilo"," getParameterAquariumm : " + _lastParameter.value.toString())
            } catch (e: Exception) {
                _parameterData.value = emptyList()

                Log.e("kilo","error getParameterAquarium : " + e.message)
            }
        }
    }

}