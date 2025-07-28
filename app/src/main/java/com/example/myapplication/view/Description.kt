package com.example.myapplication.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.viewmodel.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: String,
    productViewModel: ProductViewModel,
) {
    val productState by productViewModel.products.observeAsState()

    LaunchedEffect(productId) {
        if (productId.isNotEmpty()) {
            productViewModel.getproductById(productId)
        }
    }

    val gradient = Brush.verticalGradient(listOf(Color(0xFF4C005F), Color(0xFF9D00B7)))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        if (productState == null) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
            return@Box
        }

        val product = productState!!

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Bar
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    "Product Details",
                    fontSize = 18.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.weight(1f))
                Spacer(modifier = Modifier.size(48.dp))


            Spacer(modifier = Modifier.height(40.dp))

            // Product Image
            AsyncImage(
                model = product.imageUrl.ifEmpty { "https://via.placeholder.com/300" },
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Product Name
            Text(
                text = product.name,
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Category
            Text(
                text = "Category: ${product.category}",
                fontSize = 16.sp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Price
            Text(
                text = "Price: Rs. ${product.price}",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Description
            Text(
                text = product.description,
                fontSize = 16.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Add to Cart or Buy Now button
            Button(
                onClick = {
                    // TODO: Implement add to cart
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Add to Cart")
            }
        }
    }
}
