package com.example.aquariumtestapp.home.component.bottomSheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.data.model.UserState
import com.example.aquariumtestapp.data.viewModel.ParameterAquariumViewModel
import com.example.aquariumtestapp.home.viewModel.SelectAquariumViewModel
import com.example.aquariumtestapp.home.viewModel.StoreSelectedAquariumViewModel
import com.example.aquariumtestapp.utils.LoadingComponent
@Composable
fun listAqua(
    bottomSheetContinue : () -> Unit,
    bottomSheetAddAqua : () -> Unit,

    selectAquariumViewModel: SelectAquariumViewModel,
    storeSelectedAquariumViewModel: StoreSelectedAquariumViewModel,
    parameterAquariumViewModel: ParameterAquariumViewModel
){
    val aquariumData by selectAquariumViewModel.aquariumData
    val userState by selectAquariumViewModel.userState

    LaunchedEffect(Unit)
    {
        selectAquariumViewModel.getAquarium()

    }

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
                "Vos aquariums",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(40.dp))
            )

            when (userState) {
                is UserState.Loading -> {
                    LoadingComponent()
                }
                is UserState.Success -> {

                    LazyColumn(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 7.dp)
                    ) {
                        items(aquariumData  ?: emptyList() ) { aquarium ->
                            val isSelected = storeSelectedAquariumViewModel.selectedAquarium.value == aquarium.aquariumId

                            val backgroundColor =
                                if (isSelected) Color(0xFF63A8E7) else Color(0xFFF8F8F8)

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(7.dp)
                                    .clickable {
                                        storeSelectedAquariumViewModel.saveSelectedAquarium(aquarium.aquariumId, aquarium.name)
                                        storeSelectedAquariumViewModel.getSelectedAquariumId()
                                        storeSelectedAquariumViewModel.getSelectedAquariumName()
                                        storeSelectedAquariumViewModel.selectedAquarium.value?.let {
                                            storeSelectedAquariumViewModel.deleteSelectedAquarium(
                                                it
                                            )
                                        }
                                        parameterAquariumViewModel.getParameterAquarium(aquarium.aquariumId)
                                      //  println("Aquarium selected: ${aquarium.aquariumId} ${aquarium.name}")
                                    }
                                    .clip(RoundedCornerShape(10.dp))
                                    .border(1.dp, Color(0x176D6D6D))
                                    .background(backgroundColor)
                                    .padding(
                                        bottom = 15.dp,
                                        top = 15.dp,
                                        start = 15.dp,
                                        end = 15.dp
                                    )

                            ) {
                                Text(
                                    aquarium.name,
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )
                                Box(
                                    modifier = Modifier

                                        .clip(RoundedCornerShape(10.dp))
                                        .clickable {
                                            selectAquariumViewModel.deleteAquarium(aquarium.aquariumId)
                                        }
                                        .padding(3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Clear,
                                        contentDescription = null,
                                        tint = Color(0xAE101010),
                                        modifier = Modifier
                                            .size(20.dp)
                                    )
                                }
                            }

                        }
                    }
                }
                is UserState.Error -> {
                    Text("Aucun aquarium trouvé", fontSize = 20.sp, modifier = Modifier.padding(16.dp))
                }

                else -> {}
            }

            Button(
                onClick = {
                    println("Aquarium saved button")
                    bottomSheetContinue()
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
                onClick = {bottomSheetAddAqua()},
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
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color(0xAE101010),
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(35.dp)
                )
                Text("Ajouter un aquarium", )

            }
        }

    }
}

