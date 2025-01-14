package com.example.aquariumtestapp.ui.home.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.data.viewModel.ParameterAquariumViewModel
import com.example.aquariumtestapp.data.viewModel.StoreSelectedAquariumViewModel
import kotlinx.coroutines.launch

@Composable
fun fastScan (parameterAquariumViewModel : ParameterAquariumViewModel) {
    val context = LocalContext.current
    val storeSelectedAquariumViewModel = StoreSelectedAquariumViewModel(context)
    val lastParam = parameterAquariumViewModel.lastParameter.value

    LaunchedEffect(lastParam)
    {

    }

    Log.e("kilo","parameterData test : " + lastParam)

    val configuration = LocalConfiguration.current

    Log.e("kilo","parameterDataaaa : " + parameterAquariumViewModel.lastParameter.value.toString())
    Log.e("kilo","parameterDataaaa : " + parameterAquariumViewModel.lastParameter.value.toString())

    val pages by remember(parameterAquariumViewModel.lastParameter.value) {
        derivedStateOf {
            listOf(
                listOf(
                    Triple(parameterAquariumViewModel.lastParameter.value?.PH?.toString() ?: "_ _", "pH", Color(0x90B3EDE9)),
                    Triple(parameterAquariumViewModel.lastParameter.value?.KH?.toString() ?: "_ _", "KH", Color(0x90BFE0FF)),
                    Triple(parameterAquariumViewModel.lastParameter.value?.GH?.toString() ?: "_ _", "GH", Color(0x90F1D8E7)),
                    Triple(parameterAquariumViewModel.lastParameter.value?.NO2?.toString() ?: "_ _", "N02", Color(0x90A8E3C2)),
                    Triple(parameterAquariumViewModel.lastParameter.value?.NO3?.toString() ?: "_ _", "NO3", Color(0x908693D5))
                ),
                listOf(
                    Triple(parameterAquariumViewModel.lastParameter.value?.TA?.toString() ?: "_ _", "TA", Color(0x90B3EDE9)),
                    Triple(parameterAquariumViewModel.lastParameter.value?.CL2?.toString() ?: "_ _", "CL2", Color(0x90BFE0FF)),
                    Triple("", "", Color(0xAD949494)),
                    Triple("", "", Color(0xAD949494)),
                    Triple("", "", Color(0xAD949494))
                )
            )
        }
    }

    val state = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { pages.size }
    val coroutineScope = rememberCoroutineScope()

    //val formatter = SimpleDateFormat("MM/dd/yyyy")
    //val timestamp = parameterAquariumViewModel.lastParameter.value?.timestamp.toString()
    //val timestampRefactor = formatter.parse(timestamp)

    Box(
        modifier = Modifier

            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(30.dp),
                ambientColor = Color.Black.copy(alpha = 0.5f),
                spotColor = Color.Black.copy(alpha = 0.5f)
            )
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.80f)

    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 5.dp)
        ) {
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Fishbotica ")
                }
                append("FastScan")

            }, fontSize = 18.sp)
            Text(
                text = "Paramètres chimiques de l'aquarium",
                fontSize = 14.sp,
                color = Color(0xFF696969),
                modifier = Modifier.padding(top = 5.dp)
            )
            HorizontalPager(
                modifier = Modifier.height(configuration.screenHeightDp.dp* 0.31f),
                state = state,
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp)


                   // shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                    )
                    {
                        Row(
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, bottom = 11.dp)

                        ) {
                            pages[page].forEach { (value, label, color) ->
                                parameter(value, label, color)
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }

                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text("Dernier Scan", color = Color(0xD8696969), fontSize = 13.sp)
                DraggableIndicator(
                    modifier = Modifier,
                    state = state,
                    itemCount = pages.size,
                    onPageSelect = { page ->
                        coroutineScope.launch {
                            state.scrollToPage(page)
                        }
                    },
                )
                Text("01/01/2024", color = Color(0xD8696969), fontSize = 13.sp)
            }
        }

    }
}

