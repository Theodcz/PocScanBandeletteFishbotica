package com.example.aquariumtestapp.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun parameter (value: String, parameter: String, color: Color){
    val configuration = LocalConfiguration.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(width = 55.dp, height = configuration.screenHeightDp.dp* 0.25f)//300.dp)
            .clip(RoundedCornerShape(10.dp))

            .background(
                Brush.verticalGradient(
                colors = listOf(Color(0xFFF4F8FB), color, color) // Dégradé du blanc au vert clair
            )
        ),
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp)
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = value,
                    fontSize = 18.sp,
                    //fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D1C1A),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "mg/L",
                    fontSize = 11.sp,
                    color = Color(0xFF0D1C1A)
                )
            }

            Text(
                text = parameter,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF0D1C1A)
            )
        }

    }
}