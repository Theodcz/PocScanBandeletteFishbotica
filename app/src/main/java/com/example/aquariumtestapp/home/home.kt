package com.example.aquariumtestapp.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dokar.sheets.PeekHeight
import com.dokar.sheets.m3.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import com.example.aquariumtestapp.DataViewModel
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.data.viewModel.ParameterAquariumViewModel
import com.example.aquariumtestapp.home.component.bottomSheet.addAqua
import com.example.aquariumtestapp.home.component.bottomSheet.aquaIsAdd
import com.example.aquariumtestapp.home.component.bottomSheet.listAqua
import com.example.aquariumtestapp.home.component.exploreTask
import com.example.aquariumtestapp.home.component.fastScan
import com.example.aquariumtestapp.home.component.mesure
import com.example.aquariumtestapp.home.component.nextTest
import com.example.aquariumtestapp.home.viewModel.SelectAquariumViewModel
import com.example.aquariumtestapp.home.viewModel.StoreSelectedAquariumViewModel
import kotlinx.coroutines.launch

@Composable
fun home (

    storeSelectedAquariumViewModel: StoreSelectedAquariumViewModel,
    selectAquariumViewModel: SelectAquariumViewModel,
    parameterAquariumViewModel : ParameterAquariumViewModel
) {
    val selectedAquarium  = storeSelectedAquariumViewModel.selectedAquarium.value
    val lastParameter = parameterAquariumViewModel.lastParameter.value
    Log.e("kilo","parameterDataaaa : " + parameterAquariumViewModel.lastParameter.value.toString())

    /*LaunchedEffect(Unit) { // dans le cas ou l'utilisateur se connecte et que l'aquarium est déjà sélectionné
        selectedAquarium?.let { aquariumId ->
            parameterAquariumViewModel.getParameterAquarium(aquariumId)
        }
    }*/
    LaunchedEffect(Unit) { // dans le cas ou l'utilisateur se connecte et que l'aquarium est déjà sélectionné
        selectedAquarium?.let { aquariumId ->
            parameterAquariumViewModel.getParameterAquarium(aquariumId)
        }
    }
    val state = rememberBottomSheetState()
    val coroutineScope = rememberCoroutineScope()
    val isAddingAqua = remember { mutableStateOf("listAqua") }  // État pour gérer le contenu du BottomSheet

    fun manageNavBottomBar(nav: String) {
        isAddingAqua.value = nav
    }

    fun manageState() {
        coroutineScope.launch {
            if (state.isCollapsing) {
                state.collapse()
            } else {
                state.expand()
            }
        }
    }

    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.whiteBackground))
            .padding(top = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            exploreTask()
            nextTest ( {manageState()}, storeSelectedAquariumViewModel)
            fastScan(parameterAquariumViewModel)
            Spacer(modifier = Modifier.height(16.dp))
            mesure()
            BottomSheet(
                state = state,
                maxDimAmount = 0.5f,
                peekHeight = PeekHeight.fraction(3f),
                skipPeeked = true,
                shape = RoundedCornerShape(26.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxHeight(0.65f)
            ) {
                when (isAddingAqua.value) {
                    "listAqua" -> {
                        listAqua({manageNavBottomBar("Continue")},{manageNavBottomBar("addAqua")},selectAquariumViewModel,storeSelectedAquariumViewModel, parameterAquariumViewModel)
                    }
                    "addAqua" -> {
                        addAqua ({manageNavBottomBar("listAqua")}, {manageNavBottomBar("aquaIsAdd")})
                    }
                    "aquaIsAdd" -> {
                        aquaIsAdd {manageNavBottomBar("Continue")}
                    }
                    else -> {
                        coroutineScope.launch {
                            state.collapse()
                        }
                        manageNavBottomBar("listAqua")
                    }
                }
            }
        }


    }
}
