package com.example.aquariumtestapp.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R

@Composable
fun ArticleBoutique(image: Int, name: String, price: Double) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .size(356.dp, 110.dp)

    ) {
        Row (modifier = Modifier.padding(10.dp)){
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(0xFFF4F5F7))
                    .size(105.dp, 86.dp)
            ) {
                Image(painter = painterResource(id = image), contentDescription = "",
                    modifier = Modifier.size(105.dp, 86.dp))
            }

            Column ( modifier = Modifier.padding(start = 10.dp, top = 10.dp)){
                Text(text = name, fontSize = 14.sp, fontWeight = FontWeight.SemiBold
                )

                Row(
                    modifier = Modifier.padding(top = 5.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Text(text = "Fishbotica  .  4.9", color = Color(0XFFA6A798),
                        fontSize = 12.sp, fontWeight = FontWeight.Normal
                    )

                    Spacer(modifier = Modifier.padding(1.dp))

                    Image(painter = painterResource(id = R.drawable.star), contentDescription = "",
                        modifier = Modifier.size(12.dp, 12.dp))
                }

                Text(text = String.format("%.2f €", price), color = Color(0XFFBA5C3D),
                    fontSize = 14.sp, fontWeight = FontWeight.SemiBold
                )
            }
        }
    }


}