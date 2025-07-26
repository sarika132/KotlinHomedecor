package com.example.myapplication.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

class ListViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListViewBody()
        }
    }
}

@Composable
fun ListViewBody(){
    val images = listOf(
        R.drawable.aaa,
        R.drawable.img,
        R.drawable.cccc,
        R.drawable.dddd,
        R.drawable.eeeee,
        R.drawable.img,
        R.drawable.imgs

    )
    val name = listOf(
        "aaa",
        "bbb",
        "cccc",
        "dddd",
        "eeeee",
        "img",
        "imgs"
    )
    Scaffold { padding ->

        LazyColumn(modifier = Modifier
            .padding(padding)
            .fillMaxSize(),


            ) {
            item {

                LazyVerticalGrid(modifier = Modifier
                    .height(320.dp)
                    .fillMaxWidth(),
                    columns = GridCells.Fixed(3)
                ) {
                    items(images.size){ index: Int ->
                        Column  (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Image(
                                painterResource(images[index]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(80.dp).clip(shape = CircleShape)
                            )
                            Text(name[index])
                        }
                    }
                }
                LazyRow(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(images.size){index ->
                        Column  (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center


                        ) {
                            Image(
                                painterResource(images[index]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(80.dp).clip(shape = CircleShape)
                            )

                            Text(name[index])
                        }

                    }
                }

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    item {
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = Color.Gray)
                                .padding(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = Color.DarkGray)
                                .padding(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = Color.LightGray)
                                .padding(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = Color.Blue)
                                .padding(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = Color.Yellow)
                                .padding(10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .background(color = Color.Red)
                                .padding(10.dp)
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth().padding(20.dp)
                        .background(Color.Red)
                )
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth().padding(20.dp)
                        .background(Color.Yellow)
                )
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth().padding(20.dp)
                        .background(Color.Blue)
                )
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth().padding(20.dp)
                        .background(Color.Magenta)
                )
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth().padding(20.dp)
                        .background(Color.Green)
                )
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth().padding(20.dp)
                        .background(Color.Black)
                )



            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewListViewBody(){
    ListViewBody()
}
