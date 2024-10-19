package com.example.aquariumtestapp.history

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R


@Composable
fun history() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.whiteBackground))
            .padding(top = 17.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(356.dp, 55.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(
                            topStart = 30.dp,
                            topEnd = 30.dp,
                            bottomStart = 16.dp,
                            bottomEnd = 16.dp
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        ) {
                            append("Historique ")
                        }
                        append("FastScan")
                    },
                    color = Color.Black,
                    fontSize = 18.sp,
                )
            }

            Box(
                modifier = Modifier
                    .padding(top = 32.dp, start = 15.dp, end = 15.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .size(363.dp, 321.dp),
            ) {
                Column(
                    modifier = Modifier.padding(15.dp)
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            ) {
                                append("Données ")
                            }
                            append("chimiques")
                        },
                        color = Color.Black,
                        fontSize = 18.sp,
                    )

                    Text(
                        text = "Tendances de vos paramètres chimiques",
                        color = Color(0xFF696969),
                        fontSize = 14.sp
                    )

                    Spacer(modifier = Modifier.size(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Spacer(modifier = Modifier.width(17.dp))
                        Text(text = "Jan", color = Color.Black, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(37.dp))
                        Text(text = "Feb", color = Color.Black, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(37.dp))
                        Text(text = "Mar", color = Color.Black, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(37.dp))
                        Text(text = "Apr", color = Color.Black, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(37.dp))
                        Text(text = "Mai", color = Color.Black, fontSize = 10.sp)
                        Spacer(modifier = Modifier.width(37.dp))
                        Text(text = "Jun", color = Color.Black, fontSize = 10.sp)
                    }

                    Spacer(modifier = Modifier.size(11.dp))
                    CustomGridChart(
                        seriesDataList = listOf(
                            SeriesData(
                                points = listOf(
                                    Pair(0f, 0.5f),
                                    Pair(1f, 1.2f),
                                    Pair(2f, 2.3f),
                                    Pair(3f, 0.4f),
                                    Pair(4f, 1.2f),
                                    Pair(5f, 2.8f),
                                    Pair(6f, 1.5f),
                                ),
                                color = Color(0xFF7987FF)// Couleur de la première ligne
                            ),
                            SeriesData(
                                points = listOf(
                                    Pair(0f, 1.0f),
                                    Pair(1f, 1.5f),
                                    Pair(2f, 1.8f),
                                    Pair(3f, 0.9f),
                                    Pair(4f, 2.0f),
                                    Pair(5f, 2.2f),
                                    Pair(6f, 1.3f),
                                ),
                                color = Color(0xFFE697FF)// Couleur de la deuxième ligne
                            ),
                            SeriesData(
                                points = listOf(
                                    Pair(0f, 0.8f),
                                    Pair(1f, 1.0f),
                                    Pair(2f, 1.5f),
                                    Pair(3f, 1.2f),
                                    Pair(4f, 1.8f),
                                    Pair(5f, 2.0f),
                                    Pair(6f, 1.0f),
                                ),
                                color = Color(0xFFFFA5CB)// Couleur de la troisième ligne
                            ),
                        )
                    )

                    Legend(
                        seriesDataList = listOf(
                            SeriesData(points = emptyList(), color = Color(0xFF7987FF)), // PH
                            SeriesData(points = emptyList(), color = Color(0xFFE697FF)), // Ammonium
                            SeriesData(points = emptyList(), color = Color(0xFFFFA5CB)), // Nitrate
                        )
                    )

                }
            }
        }
    }
}


@Composable
fun CustomGridChart(seriesDataList: List<SeriesData>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .size(135.dp)
    ) {
        val width = size.width
        val height = size.height

        // Dimensions de la grille
        val rows = 3
        val columns = 6

        val rowHeight = height / rows
        val columnWidth = width / columns

        // Dessiner la grille
        for (i in 1 until rows) {
            drawLine(
                color = Color(0xFFF1F1F1),
                start = Offset(1f, i * rowHeight),
                end = Offset(width, i * rowHeight),
                strokeWidth = 5f
            )
        }
        for (i in 1 until columns) {
            drawLine(
                color = Color(0xFFF1F1F1),
                start = Offset(i * columnWidth, 1f),
                end = Offset(i * columnWidth, height),
                strokeWidth = 5f
            )
        }

        // Dessiner les lignes extérieures
        drawLine(
            color = Color(0xFFF1F1F1),
            start = Offset(0f, 0f), // Ligne du haut
            end = Offset(width, 0f),
            strokeWidth = 5f
        )

        drawLine(
            color = Color(0xFFF1F1F1),
            start = Offset(0f, height), // Ligne du bas
            end = Offset(width, height),
            strokeWidth = 5f
        )

        drawLine(
            color = Color(0xFFF1F1F1),
            start = Offset(0f, 0f), // Ligne à gauche
            end = Offset(0f, height),
            strokeWidth = 5f
        )

        drawLine(
            color = Color(0xFFF1F1F1),
            start = Offset(width, 0f), // Ligne à droite
            end = Offset(width, height),
            strokeWidth = 5f
        )

        // Dessiner les lignes de données
        for (seriesData in seriesDataList) {
            val points = seriesData.points
            if (points.isNotEmpty()) {
                // Dessiner le premier point séparément pour s'assurer qu'il est visible
                val firstPoint = points.first()
                var startX = firstPoint.first * columnWidth
                var startY = height - (firstPoint.second * rowHeight)

                // Dessiner le premier point
                drawCircle(
                    color = seriesData.color, // Couleur du premier point
                    radius = 10f,
                    center = Offset(startX, startY)
                )

                // Dessiner la ligne à partir du premier point
                for (i in 1 until points.size) {
                    val currentPoint = points[i]

                    // Calculer les coordonnées en fonction des données
                    val endX = currentPoint.first * columnWidth
                    val endY = height - (currentPoint.second * rowHeight)

                    // Dessiner la ligne entre les points avec la couleur correspondante
                    drawLine(
                        color = seriesData.color, // Couleur de la ligne
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 4f
                    )

                    // Dessiner le point actuel
                    drawCircle(
                        color = seriesData.color, // Couleur des points
                        radius = 10f,
                        center = Offset(endX, endY)
                    )

                    // Mettre à jour le point de départ pour la prochaine itération
                    startX = endX
                    startY = endY
                }
            }
        }
    }
}


@Composable
fun Legend(seriesDataList: List<SeriesData>) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        for (seriesData in seriesDataList) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Dessiner le cercle
                Canvas(modifier = Modifier.size(8.dp)) {
                    drawCircle(color = seriesData.color)
                }
                Spacer(modifier = Modifier.width(5.dp))
                // Ajouter le texte
                Text(
                    text = when (seriesData.color) {
                        Color(0xFF7987FF) -> "PH"
                        Color(0xFFE697FF) -> "GH"
                        Color(0xFFFFA5CB) -> "KH"
                        else -> ""
                    }, fontSize = 12.sp
                )
            }
        }
    }
}

data class SeriesData(val points: List<Pair<Float, Float>>, val color: Color)
