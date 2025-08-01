package com.example.myapplication.view

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.ProductModel
import com.example.myapplication.repository.ProductRepositoryImpl
import com.example.myapplication.utils.ImageUtils
import com.example.myapplication.viewmodel.ProductViewModel

// MainActivity: Entry point switching between Add Product and Music Collection screens
class MainActivity : ComponentActivity() {

    lateinit var imageUtils: ImageUtils
    var selectedImageUri by mutableStateOf<Uri?>(null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        imageUtils = ImageUtils(this, this)
        imageUtils.registerLaunchers { uri ->
            selectedImageUri = uri
        }

        setContent {
            var currentScreen by remember { mutableStateOf("AddProduct") }

            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { Text("My Application") },
                        actions = {
                            Button(onClick = { currentScreen = "AddProduct" }) {
                                Text("Add Product")
                            }
                            Button(onClick = { currentScreen = "MusicCollection" }) {
                                Text("Music Collection")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    when (currentScreen) {
                        "AddProduct" -> AddProductBody(
                            selectedImageUri = selectedImageUri,
                            onPickImage = { imageUtils.launchImagePicker() }
                        )
                        "MusicCollection" -> MusicCollectionScreen()
                    }
                }
            }
        }
    }
}

// Your AddProductBody composable — mostly unchanged, but now better organized for recomposition
@Composable
fun AddProductBody(
    selectedImageUri: Uri?,
    onPickImage: () -> Unit
) {
    var pName by remember { mutableStateOf("") }
    var pPrice by remember { mutableStateOf("") }
    var pDesc by remember { mutableStateOf("") }

    val repo = remember { ProductRepositoryImpl() }
    val viewModel = remember { ProductViewModel(repo) }

    val context = LocalContext.current
    val activity = context as? Activity

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onPickImage()
                        }
                        .padding(10.dp)
                ) {
                    if (selectedImageUri != null) {
                        AsyncImage(
                            model = selectedImageUri,
                            contentDescription = "Selected Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.img),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }

                OutlinedTextField(
                    value = pName,
                    onValueChange = { pName = it },
                    placeholder = { Text("Enter product name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = pPrice,
                    onValueChange = { pPrice = it },
                    placeholder = { Text("Enter price") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = pDesc,
                    onValueChange = { pDesc = it },
                    placeholder = { Text("Enter Description") },
                    minLines = 3,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        val image = selectedImageUri?.toString() ?: ""
                        val price = pPrice.toDoubleOrNull() ?: 0.0

                        val model = ProductModel(
                            id = "",
                            name = pName,
                            productPrice = price,
                            productDesc = pDesc,
                            productImage = image
                        )

                        viewModel.addProduct(model) { success, msg ->
                            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
                            if (success) {
                                activity?.finish()
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text("Add Product")
                }
            }
        }
    }
}

// Your MusicCollectionScreen and helpers (unchanged from what you provided)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MusicCollectionScreen() {
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val collectionItems = listOf(
        CollectionCard("Favourite", "Your choice", painterResource(R.drawable.img)),
        CollectionCard("New Collection", "Latest", painterResource(R.drawable.img))
    )

    val newCollectionItems = listOf(
        Album("Dandelions", "Ruth B", painterResource(R.drawable.img)),
        Album("Blue", "Yung Kaf", painterResource(R.drawable.img)),
        Album("Dhairya", "Sajan Raj Vaidya", painterResource(R.drawable.img)),
        Album("Night Changes", "One Direction", painterResource(R.drawable.img)),
        Album("Sarara Sarara", "Kohar Sing Limbu", painterResource(R.drawable.img)),
        Album("Furfuri", "Kohar Sing Limbu", painterResource(R.drawable.img))
    )

    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Top Row: Menu + Title + Circle Avatar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Welcome to Ghar Sanshar",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                leadingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                },
                placeholder = { Text("Search Here", color = Color.White.copy(alpha = 0.7f)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color(0xFF6A1B9A),
                    textColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
                    cursorColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(10.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Collection Section Title
            Text(
                "Collection",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Collection Cards Row
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                collectionItems.forEach { item ->
                    CollectionCardItem(item)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // New Collection with dropdown
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "New Collection",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        painter = painterResource(
                            if (expanded)
                                R.drawable.ic_expand_less
                            else
                                R.drawable.ic_expand_more
                        ),
                        contentDescription = "Expand",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (expanded) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxHeight(0.75f)
                ) {
                    items(newCollectionItems) { album ->
                        AlbumItem(album)
                    }
                }
            }
        }
    }
}

@Composable
fun CollectionCardItem(item: CollectionCard) {
    Card(
        modifier = Modifier
            .weight(1f)
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF7B1FA2).copy(alpha = 0.7f))
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = item.icon,
                contentDescription = item.title,
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = item.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = item.subtitle,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun AlbumItem(album: Album) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: Add click action */ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.size(120.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF9C27B0))
        ) {
            Image(
                painter = album.image,
                contentDescription = album.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            album.title,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Text(
            album.artist,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 12.sp
        )
    }
}

data class CollectionCard(val title: String, val subtitle: String, val icon: Painter)
data class Album(val title: String, val artist: String, val image: Painter)

// Optional preview for AddProductBody
@Preview(showBackground = true)
@Composable
fun PreviewAddProductBody() {
    AddProductBody(selectedImageUri = null, onPickImage = {})
}

// Optional preview for MusicCollectionScreen
@Preview(showBackground = true)
@Composable
fun PreviewMusicCollectionScreen() {
    MusicCollectionScreen()
}
