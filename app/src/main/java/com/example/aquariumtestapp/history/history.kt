package com.example.aquariumtestapp.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import com.example.aquariumtestapp.R


@Composable
fun history() {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.whiteBackground))
            .padding(top = 16.dp)
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
                    .padding(top = 32.dp)
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
            ) {
                LineChartGraph()
            }
        }
    }
}

@Composable
fun LineChartGraph() {
    // Liste des points de données (coordonnées X et Y)
    val pointsData: List<Point> = listOf(
        Point(0f, 40f),  // Janvier
        Point(1f, 60f),  // Février
        Point(2f, 50f),  // Mars
        Point(3f, 80f),  // Avril
        Point(4f, 90f),  // Mai
        Point(5f, 70f),   // Juin
        Point(6f, 60f)   // Juillet
    )

    // Initialisation de l'axe X avec les mois
    val xAxisData = AxisData.Builder()
        .axisStepSize(63.dp)
        .steps(7) // Définir des steps moins nombreux pour ne pas aller jusqu'à la fin
        .shouldDrawAxisLineTillEnd(false) // Arrêter le tracé de la ligne avant la fin
        .build()

// Initialisation de l'axe Y avec les valeurs de TDS
    val yAxisData = AxisData.Builder()
        .steps(3)
        .shouldDrawAxisLineTillEnd(false) // Pareil pour l'axe Y
        .build()

    // Création du LineChartData pour configurer le graphique
    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    lineStyle = LineStyle(color = Color.Blue)
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = Color.LightGray),
        backgroundColor = Color.White
    )

    // Affichage du graphique avec LineChart
    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .size(333.dp, 200.dp),
        lineChartData = lineChartData
    )
}