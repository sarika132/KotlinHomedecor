//package com.example.myapplication.view
//
//import android.widget.Toast
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.*
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
//
//// Dummy ProductModel class for illustration
//data class ProductModel(
//    val productId: String,
//    val productName: String,
//    val imageUrl: String,
//    val price: Int
//)
//
//// Dummy CartItem class to hold product and quantity
//data class CartItem(
//    val product: ProductModel,
//    val quantity: Int
//)
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
//
//@Composable
//fun CartItemCard(
//    product: ProductModel,
//    quantity: Int,
//    onAdd: () -> Unit,
//    onRemove: () -> Unit,
//    onDelete: () -> Unit,
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth(),
//        shape = RoundedCornerShape(12.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.3f))
//    ) {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(12.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            AsyncImage(
//                model = product.imageUrl,
//                contentDescription = "${product.productName} image",
//                modifier = Modifier
//                    .size(60.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                contentScale = ContentScale.Crop
//            )
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Column(modifier = Modifier.weight(1f)) {
//                Text(
//                    text = product.productName,
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    maxLines = 1
//                )
//                Text(
//                    text = "Rs. ${product.price}",
//                    color = Color.Gray,
//                    fontSize = 14.sp
//                )
//            }
//
//            Spacer(modifier = Modifier.width(12.dp))
//
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                IconButton(
//                    onClick = onRemove,
//                    modifier = Modifier.size(30.dp)
//                ) {
//                    Icon(
//                        Icons.Default.RemoveCircle,
//                        contentDescription = "Decrease quantity",
//                        tint = Color(0xFFE91E63)
//                    )
//                }
//
//                Text(
//                    quantity.toString(),
//                    color = Color.White,
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(horizontal = 4.dp)
//                )
//
//                IconButton(
//                    onClick = onAdd,
//                    modifier = Modifier.size(30.dp)
//                ) {
//                    Icon(
//                        Icons.Default.AddCircle,
//                        contentDescription = "Increase quantity",
//                        tint = Color(0xFFE91E63)
//                    )
//                }
//            }
//
//            IconButton(
//                onClick = onDelete,
//                modifier = Modifier.size(30.dp)
//            ) {
//                Icon(
//                    Icons.Default.Delete,
//                    contentDescription = "Remove from cart",
//                    tint = Color.Red
//                )
//            }
//        }
//    }
//}
