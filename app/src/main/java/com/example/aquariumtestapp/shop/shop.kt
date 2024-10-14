package com.example.aquariumtestapp.shop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
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
                    text = "Boutique Fishbotica",
                    modifier = Modifier.padding(10.dp),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
        }

        LazyColumn(modifier = Modifier.padding(top = 200.dp)) {
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