package com.example.aquariumtestapp.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.camera.CameraActivity
import com.example.aquariumtestapp.home.component.DraggableIndicator
import com.example.aquariumtestapp.home.component.exploreTask
import com.example.aquariumtestapp.home.component.fastScan
import com.example.aquariumtestapp.home.component.mesure
import com.example.aquariumtestapp.home.component.nextTest
import kotlinx.coroutines.launch

@Composable
fun home () {
    val context = LocalContext.current

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

        }

    }
}