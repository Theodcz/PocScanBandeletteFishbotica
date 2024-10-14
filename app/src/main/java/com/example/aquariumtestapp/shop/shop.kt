package com.example.aquariumtestapp.shop

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aquariumtestapp.R

@Composable
fun shop() {

    val context = LocalContext.current

    val articles = listOf(
        Article(R.drawable.aquatest, "Bandelettes FastScan", 8.99),
        Article(R.drawable.aquatest, "Substrat Sol", 49.00),
        Article(R.drawable.aquatest, "Flacon pH", 59.00)
    )

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
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)) {
                        append("Boutique ")
                    }
                    append("Fishbotica")
                },
                modifier = Modifier.padding(10.dp),
                color = Color.Black,
                fontSize = 18.sp,
            )

            Spacer(modifier = Modifier.padding(5.dp))

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .border(
                        1.dp,
                        Color(0xFFC9CEDA),
                        shape = RoundedCornerShape(3.dp),
                    ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White, // Rend le fond transparent
                    unfocusedContainerColor = Color.White, // Rend le fond transparent
                    focusedIndicatorColor = Color.Transparent,  // Supprime la ligne lors du focus
                    unfocusedIndicatorColor = Color.Transparent // Supprime la ligne sans focus
                ),
                value = "",
                placeholder = {
                    Row(
                        modifier = Modifier.padding(0.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Vous recherchez un produit ...",
                            fontSize = 13.sp,
                            color = Color(0xFF888C93)
                        )

                        Spacer(modifier = Modifier.padding(start = 120.dp))

                        Image(
                            painter = painterResource(id = R.drawable.setting),
                            contentDescription = "",
                            modifier = Modifier.size(15.dp)
                        )

                    }
                },
                onValueChange = {

                },
                singleLine = true,
            )

            Row(modifier = Modifier.padding(top = 10.dp)) {
                // Image header poisson
                Image(
                    painter = painterResource(id = R.drawable.headeraqua),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(356.dp, 145.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.FillWidth // Étire l'image pour qu'elle prenne toute la largeur
                )
            }


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, top = 280.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(horizontalArrangement = Arrangement.Start) {
                Text(
                    text = "Les produits",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
        
        LazyColumn(modifier = Modifier.padding(top = 330.dp)) {
            items(articles.count()) {
                val article = articles[it]

                ArticleBoutique(
                    image = article.image,
                    name = article.name,
                    price = article.price
                )
                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}


data class Article(
    val image: Int,
    val name: String,
    val price: Double
)