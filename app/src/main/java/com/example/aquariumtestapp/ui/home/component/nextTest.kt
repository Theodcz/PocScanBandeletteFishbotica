package com.example.aquariumtestapp.ui.home.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.data.viewModel.StoreSelectedAquariumViewModel

@Composable
fun nextTest (
    state : () -> Unit,

    storeSelectedAquariumViewModel : StoreSelectedAquariumViewModel,
) {
    storeSelectedAquariumViewModel.getSelectedAquariumId()
    storeSelectedAquariumViewModel.getSelectedAquariumName()
    var name = ""
    LaunchedEffect(storeSelectedAquariumViewModel.aquariumName.value) {
        println("aquariumNamee: ${storeSelectedAquariumViewModel.aquariumName.value}")
        name = storeSelectedAquariumViewModel.aquariumName.value
       // println("name: " )
    }

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .height(170.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                // Premiere vague
                val waveHeight1 = size.height * 0.6f
                val path1 = Path().apply {
                    moveTo(0f, waveHeight1)
                    quadraticTo(
                        size.width * 0.0f, waveHeight1  ,
                        size.width * 0.01f, waveHeight1 +3

                    )
                    quadraticTo(
                        size.width * 0.1f, waveHeight1 + 50f,
                        size.width * 0.2f, waveHeight1 + 10f

                    )
                    quadraticTo(
                        size.width * 0.6f, waveHeight1 - 150f,
                        size.width * 0.8f, waveHeight1 - 40f
                    )
                    quadraticTo(
                        size.width , waveHeight1 + 50f,
                        size.width , waveHeight1 + 600f
                    )

                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                drawPath(path1, color = Color(0x742496FF), style = Fill)

                // Deuxième vague (superposée légèrement au-dessus)
                val waveHeight2 = size.height * 0.47f
                val path2 = Path().apply {
                    moveTo(0f, waveHeight2)

                    quadraticTo(
                        size.width * 0.1f, waveHeight2 - 10f, // Légère baisse pour rendre la courbe plus douce
                        size.width * 0.3f, waveHeight2 + 70f   // Point d'arrivée légèrement plus haut
                    )

                    // Deuxième point de contrôle pour la descente
                    quadraticTo(
                        size.width * 0.4f, waveHeight2 + 100f,  // Point d'arrivée légèrement plus haut pour rendre la courbe lisse
                        size.width * 0.5f, waveHeight2 + 80f  // Laisse la courbe descendre
                    )
                    quadraticTo(
                        size.width * 0.85f, waveHeight2 - 50f,
                        size.width, waveHeight2 + 50f
                    )
                    lineTo(size.width, size.height)
                    lineTo(0f, size.height)
                    close()
                }
                val gradientBrush = Brush.linearGradient(
                    colors = listOf(Color(0x72165A99), Color(0x72165A99)),
                    start = Offset(0f, waveHeight1),
                    end = Offset(size.width, waveHeight1)
                )

                drawPath(path2, brush =gradientBrush, style = Fill)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 15.dp, end = 7.dp)
            ){
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(bottom = 20.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier
                            //.fillMaxHeight()
                            .padding(top = 20.dp)
                    ){
                        Text(
                            "Jeudi : 12/08",
                            color = Color(0xFF0D1C1A),
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 22.sp,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )
                        Text(
                            "Prochain Test",
                            color = Color(0xFF90A5B4),
                            fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(bottom = 7.dp)
                        )

                    }
                    Button(
                        onClick = { state() },
                        modifier = Modifier
                            .height(35.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                            Text(
                                storeSelectedAquariumViewModel.aquariumName.value,
                                color = Color.Black,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 5.dp)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_down_arrow),
                                contentDescription = "Open list of aquariums",
                                tint = Color.Black
                            )
                        }
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_nextscan), // Remplace "nom_de_ton_image" par le nom de ton fichier image sans l'extension
                    contentDescription = null, // Description pour l'accessibilité, si nécessaire
                    modifier = Modifier
                        .fillMaxWidth(0.75f) ,

                    contentScale = ContentScale.Crop // Ajuste la mise à l'échelle selon tes besoins
                )
            }

        }}
}

