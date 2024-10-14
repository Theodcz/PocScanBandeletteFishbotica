package com.example.aquariumtestapp.home.component


import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R
import com.example.aquariumtestapp.camera.CameraActivity
import com.example.aquariumtestapp.ui.theme.gray

@Composable
fun mesure () {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(13.dp))
            .background(color = Color(0xFF63A7E6))
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.56f)

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ){
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 10.dp)
            )
            {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(25.dp) // Taille du cercle
                        .background(Color.White, shape = CircleShape) // Fond blanc avec forme circulaire
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_scan),
                        contentDescription = "Open Camera",
                        modifier = Modifier.size(20.dp),
                        tint = Color(0xFF63A7E6)
                    )
                }
                Text(
                    text = "Prendre vos mesures",
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }

            Button(
                onClick = {
                    context.startActivity(Intent(context, CameraActivity::class.java))
                },
                contentPadding = PaddingValues(end = 15.dp, start = 15.dp),
                colors = buttonColors(containerColor = Color(0xFF63A7E6)),
            ) {
                Text(text = "Scanner", color = Color.White)
            }
        }

    }
}