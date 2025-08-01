//package com.example.myapplication.view
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.RemoveCircle
//import androidx.compose.material.icons.filled.AddCircle
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.AsyncImage
//import com.example.myapplication.model.ProductModel
//import com.example.myapplication.viewmodel.CartViewModel
//import java.io.File
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun CartScreen( cartViewModel: CartViewModel) {
//    val context = LocalContext.current
//    val cartItems by cartViewModel.cartItems.observeAsState(emptyList())
//    val isLoading by cartViewModel.isLoading.observeAsState(false)
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("My Cart", color = Color.White, fontWeight = FontWeight.Bold) },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
//                    }
//                },
//                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF4A004A))
//            )
//        },
//        containerColor = Color(0xFF1C0038)
//    ) { padding ->
//
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//        ) {
//            when {
//                isLoading -> {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        CircularProgressIndicator(color = Color(0xFFE91E63))
//                    }
//                }
//
//                cartItems.isEmpty() -> {
//                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                        Text("Your cart is empty", color = Color.Gray, fontSize = 18.sp)
//                    }
//                }
//
//                else -> {
//                    Column(modifier = Modifier.fillMaxSize()) {
//                        LazyColumn(
//                            modifier = Modifier.weight(1f),
//                            contentPadding = PaddingValues(16.dp),
//                            verticalArrangement = Arrangement.spacedBy(12.dp)
//                        ) {
//                            items(cartItems) { cartItem ->
//                                CartItemCard(
//                                    product = cartItem.product,
//                                    quantity = cartItem.quantity,
//                                    onAdd = { cartViewModel.increaseQuantity(cartItem.product.productId) },
//                                    onRemove = { cartViewModel.decreaseQuantity(cartItem.product.productId) },
//                                    onDelete = {
//                                        cartViewModel.removeFromCart(cartItem.product.productId) { success, message ->
//                                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
//                                        }
//                                    }
//                                )
//                            }
//                        }
//
//                        // Total price & Checkout button
//                        val totalPrice = cartItems.sumOf { it.product.price * it.quantity }
//
//                        Column(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .background(Color.Black.copy(alpha = 0.3f))
//                                .padding(16.dp)
//                        ) {
//                            Text(
//                                "Total: Rs. $totalPrice",
//                                color = Color.White,
//                                fontSize = 20.sp,
//                                fontWeight = FontWeight.Bold
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            Button(
//                                onClick = {
//                                    // TODO: Implement checkout
//                                    Toast.makeText(context, "Checkout clicked", Toast.LENGTH_SHORT).show()
//                                },
//                                modifier = Modifier.fillMaxWidth(),
//                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63))
//                            ) {
//                                Text("Checkout", color = Color.White, fontSize = 16.sp)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
