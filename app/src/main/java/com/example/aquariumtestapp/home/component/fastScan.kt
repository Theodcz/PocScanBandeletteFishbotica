package com.example.aquariumtestapp.home.component

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun fastScan () {
    val configuration = LocalConfiguration.current
    val pages = remember {
        listOf(
            listOf(
                Triple("7.5", "pH", Color(0x90B3EDE9)),
                Triple("8.2", "KH", Color(0x90BFE0FF)),
                Triple("20", "GH", Color(0x90F1D8E7)),
                Triple("100", "N02", Color(0x90A8E3C2)),
                Triple("400", "NO3", Color(0x908693D5))
            ),
            listOf(
                Triple("7.5", "TA", Color(0x90B3EDE9)),
                Triple("8.2", "CL2", Color(0x90BFE0FF)),
                Triple("", "", Color(0x90F1D8E7)),
                Triple("", "", Color(0x90A8E3C2)),
                Triple("", "", Color(0x908693D5))
            )
        )
    }

    val state = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { pages.size }
    val coroutineScope = rememberCoroutineScope()

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
                text = "Paramètre chimiques de l'aquarium",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
            HorizontalPager(
                modifier = Modifier.height(configuration.screenHeightDp.dp* 0.31f),
                state = state,
            ) { page ->
                Card(
                    modifier = Modifier
                        .fillMaxSize().padding(0.dp)

                   // shape = RoundedCornerShape(8.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
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
                Text("01/10/2021", color = Color(0xD8696969), fontSize = 13.sp)
            }
        }

    }
}

