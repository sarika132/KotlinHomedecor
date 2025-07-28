package com.example.myapplication.view

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.repository.ProductRepositoryImpl
import com.example.myapplication.viewmodel.ProductViewModel

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardBody()
        }
    }
}

@Composable
fun DashboardBody() {
    val context = LocalContext.current
    val activity = context as? Activity

    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }

    val decorProducts by viewModel.allProduct.observeAsState(initial = emptyList())
    val savedProducts by viewModel.newProducts.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getAllProduct()
        viewModel.getNewProducts()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Decor Dashboard") })
        },
        content = { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {

                item {
                    Text(
                        text = "Decor Products",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(decorProducts) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(15.dp)) {
                            Text(product.name)
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("${product.productCount} Products")
                                IconButton(onClick = {
                                    Toast.makeText(context, "Viewing ${product.name}", Toast.LENGTH_SHORT).show()
                                }) {
                                    Icon(Icons.Default.ShoppingCart, contentDescription = null)
                                }
                            }
                        }
                    }
                }

                item {
                    Text(
                        text = "Saved Products",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                items(savedProducts) { product ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 8.dp)
                    ) {
                        Column(modifier = Modifier.padding(15.dp)) {
                            Text(product.name)
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                IconButton(
                                    onClick = {
                                        viewModel.removeproductFromNewProducts(product.id) { success, message ->
                                            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                                        }
                                    },
                                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Red)
                                ) {
                                    Icon(Icons.Default.Favorite, contentDescription = null)
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDashboard() {
    DashboardBody()
}
