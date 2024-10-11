package com.example.aquariumtestapp.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.aquariumtestapp.SupabaseViewModel

@Composable
fun addAqua(viewModel: SupabaseViewModel = viewModel(),) {
    var name by remember { mutableStateOf("") }
    var volume by remember { mutableStateOf("")}

    Box(
        modifier = Modifier.padding(top = 15.dp, bottom = 35.dp, start = 15.dp, end = 15.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxSize()
        )
        {
            Text(
                "Ajouter un aquarium",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(40.dp))
            )
            BasicTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .background(Color(0x08000000))
                    .padding(16.dp)
                    ,
                textStyle = TextStyle(color = Color.Black),
                decorationBox = { innerTextField ->
                    if (name.isEmpty()) {
                        Text(text = "Nom :", color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            BasicTextField(
                value = volume,
                onValueChange = {
                    volume = it
                },
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .background(Color(0x08000000))
                    .padding(16.dp),
                textStyle = TextStyle(color = Color.Black),
                decorationBox = { innerTextField ->
                    if (volume.isEmpty()) {
                        Text(text = "Volume :", color = Color.Gray)
                    }
                    innerTextField()
                }
            )
            Button(
                onClick = {
                    //viewModel.saveAquarium(name, volume.toInt())
                          },
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
                Text("Continue")
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(25),
                colors = ButtonColors(
                    containerColor = Color(0x08000000),
                    contentColor= Color.Black,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Gray),
                contentPadding = PaddingValues(13.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
            {
                Text("Retour", )

            }
        }

    }
}