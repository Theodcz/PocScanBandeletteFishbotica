package com.example.aquariumtestapp.home.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.aquariumtestapp.data.SupabaseViewModel
import com.example.aquariumtestapp.home.viewModel.SelectAquariumViewModel


@Composable
fun addAqua(
    bottomSheetListAqua : () -> Unit,
    bottomSheetAquaIsAdd : () -> Unit,

    viewModel: SelectAquariumViewModel = viewModel(),) {

    var name by remember { mutableStateOf("") }
    var volume by remember { mutableStateOf("")}
    var nameError by remember { mutableStateOf(false) }
    var volumeError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.padding(top = 10.dp,  start = 15.dp, end = 15.dp)
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
                    nameError = false
                },
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0x08000000))
                    .padding(16.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp  // Fixe la taille de la police pour éviter le rétrécissement
                ),
                decorationBox = { innerTextField ->
                    if (name.isEmpty()) {
                        Text(text = "Nom :", color = Color.Gray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )
            if (nameError) {
                Text("Le nom ne peut pas être vide", color = Color.Red, fontSize = 12.sp)
            }
            else {
                Text("")
            }
            BasicTextField(
                value = volume,
                onValueChange = {
                    volume = it
                    volumeError = false
                },
                modifier = Modifier
                    .padding(bottom = 2.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0x08000000))
                    .padding(16.dp),
                textStyle = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp  // Fixe la taille de la police pour éviter le rétrécissement
                ),
                decorationBox = { innerTextField ->
                    if (volume.isEmpty()) {
                        Text(text = "Volume :", color = Color.Gray, fontSize = 16.sp)
                    }
                    innerTextField()
                }
            )
            if (volumeError) {
                Text("Valeur invalide", color = Color.Red, fontSize = 12.sp)
            }
            else {
                Text("")
            }
            Button(
                onClick = {
                    // Validation des champs
                    var valid = true
                    if (name.isBlank()) {
                        nameError = true
                        valid = false
                    }
                    val volumeInt = volume.toIntOrNull()
                    if (volume.isBlank() || (volumeInt == null) || (volumeInt <= 0)) {
                        volumeError = true
                        errorMessage = "Veuillez entrer un volume valide (nombre positif)."
                        valid = false
                    }

                    if (valid) {
                        viewModel.saveAquarium(name, volumeInt ?: 0)
                        println("Aquarium saved button")
                        bottomSheetAquaIsAdd()
                    }
                          },
                shape = RoundedCornerShape(30),
                colors = ButtonColors(
                    containerColor = Color(0xFF63A8E7),
                    contentColor= Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.Gray),
                contentPadding = PaddingValues(13.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)

            )
            {
                Text("Continue")
            }
            Button(
                onClick = {bottomSheetListAqua()},
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