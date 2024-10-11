package com.example.aquariumtestapp.home

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dokar.sheets.PeekHeight
import com.dokar.sheets.m3.BottomSheet
import com.dokar.sheets.rememberBottomSheetState
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.home.component.addAqua
import com.example.aquariumtestapp.home.component.exploreTask
import com.example.aquariumtestapp.home.component.fastScan
import com.example.aquariumtestapp.home.component.mesure
import com.example.aquariumtestapp.home.component.nextTest

@Composable
fun home (viewModel: HomeViewModel = viewModel()) {
    val context = LocalContext.current

    val state = rememberBottomSheetState()


    LaunchedEffect(viewModel.stateSheet.value)
    {
        if(viewModel.stateSheet.value) {
            state.expand()
        }
        else {
            state.collapse()
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
            horizontalAlignment = Alignment.CenterHorizontally,  // Aligner les éléments horizontalement au centre
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            exploreTask()
            nextTest()
            fastScan()
            Spacer(modifier = Modifier.height(16.dp))
            mesure()
            BottomSheet(
                state = state,
                maxDimAmount = 0.4f,
                peekHeight = PeekHeight.fraction(3f),
                skipPeeked = true,
                shape = RoundedCornerShape(26.dp),
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxHeight(0.5f)

            ) {
                addAqua()
            }

        }


    }
}