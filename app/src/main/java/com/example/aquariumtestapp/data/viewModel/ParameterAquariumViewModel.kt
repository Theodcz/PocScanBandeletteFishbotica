package com.example.aquariumtestapp.data.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aquariumtestapp.data.model.ParameterAquarium
import com.example.aquariumtestapp.data.repository.ParameterAquariumRepository
import kotlinx.coroutines.launch

class ParameterAquariumViewModel : ViewModel() {

    private val repository = ParameterAquariumRepository()
    private val _parameterData = mutableStateOf<List<ParameterAquarium>>(emptyList())
    val parameterData: State<List<ParameterAquarium>> = _parameterData
    fun postParameterAquarium(parameter: ParameterAquarium) {


        viewModelScope.launch {
            try {
                repository.postParameterAquarium(parameter)

            } catch (e: Exception) {
                Log.e("kilo","Error postParameterAquarium view model : ${e.message}")
            }
        }
    }


    fun getAquarium(idAquarium : Int) {

        viewModelScope.launch {
            try {
                _parameterData.value = repository.getParameterAquarium(idAquarium)
            } catch (e: Exception) {
                _parameterData.value = emptyList()
                try {

                } catch (e: Exception) {
                    _parameterData.value = emptyList()
                }
                Log.e("kilo","error getParameterAquarium : " + e.message)
            }
        }
    }

}