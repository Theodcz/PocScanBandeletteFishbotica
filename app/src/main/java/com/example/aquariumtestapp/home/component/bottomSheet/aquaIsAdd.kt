package com.example.aquariumtestapp.home.component.bottomSheet

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R

@Composable
fun aquaIsAdd(bottomSheetListAqua : () -> Unit) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(R.drawable.ic_aquaisadd),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .padding(bottom = 35.dp),
                contentScale = ContentScale.Crop // Ajuste la mise à l'échelle selon tes besoins
            )
            Text(
                "Aquarium ajouté",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
            )
        }
        Button(
            onClick = {bottomSheetListAqua()},
            shape = RoundedCornerShape(30),
            colors = ButtonColors(
                containerColor = Color(0xFF63A8E7),
                contentColor= Color.White,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.Gray),
            contentPadding = PaddingValues(13.dp),
            modifier = Modifier.fillMaxWidth()
        )
        {
            Text("Suivant")
        }
    }

}