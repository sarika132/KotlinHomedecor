package com.example.myapplication.view

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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.model.ProductModel
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardBody() {
    val context = LocalContext.current
    val viewModel = remember { ProductViewModel(ProductRepositoryImpl()) }

    val decorProducts by viewModel.allProducts.observeAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.getAllProduct()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Decor Dashboard") })
        }
    ) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item {
                Text(
                    text = "Decor Products",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(decorProducts.filterNotNull()) { product: ProductModel ->
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
                            Text("Price: ${product.productPrice}")
                            IconButton(onClick = {
                                Toast.makeText(context, "Viewing ${product.name}", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(Icons.Default.ShoppingCart, contentDescription = null)
                            }
                        }
                    }
                }
            }
        }
    }
}
