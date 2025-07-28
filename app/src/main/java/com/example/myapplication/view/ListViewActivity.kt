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


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


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
    Column(modifier=
        Modifier
            .fillMaxSize()
            .fillMaxSize().background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 19.dp, top = 19.dp, end = 19.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        )
        {
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = null,
                modifier = Modifier
                    .height(50.dp)
                    .width(50.dp)
                    .clip(shape = RoundedCornerShape(80.dp))
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 17.dp),
        ) {
            Text(
                text = "Decor Section", style =
                    TextStyle(
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold, color = Color.Cyan,
                        fontStyle = FontStyle.Normal
                    )
            )
            Text(
                "Decor you home as you want", style = TextStyle(
                    fontWeight = FontWeight.SemiBold, color = Color.Cyan
                )
            )
        }
        Row (
            modifier = Modifier
                .padding(start = 17.dp, top = 40.dp, end = 2.dp, bottom = 2.dp),
        ) {
            //First Card
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.White)
                    .width(160.dp)
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.bbb),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    Text(
                        text = "Cotton Pillow Case", style = TextStyle(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        )
                    )
                    Text(text = "Rs.800", color = Color.Gray)
                }

            }
            Spacer(modifier = Modifier.width(35.dp))

            // Second Card
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.White)
                    .width(160.dp)
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.aaa),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    Text(
                        text = "Curve Printerest Mirror", style = TextStyle(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        )
                    )
                    Text(text = "Rs.1800", color = Color.Gray)
                }

            }

        }
        Row (
            modifier = Modifier
                .padding(start = 17.dp, top = 25.dp, end = 2.dp, bottom = 2.dp),
        ) {
            //Third Card
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.White)
                    .width(160.dp)
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.login),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    Text(
                        text = "Italian Carpet", style = TextStyle(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        )
                    )
                    Text(text = "Rs.4000", color = Color.Gray)
                }

            }
            Spacer(modifier = Modifier.width(35.dp))

            // Forth Card
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.White)
                    .width(160.dp)
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.cccc),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    Text(
                        text = "Cotton Bedsheet Set", style = TextStyle(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        )
                    )
                    Text(text = "Rs.2500", color = Color.Gray)
                }

            }

        }
        Row (
            modifier = Modifier
                .padding(start = 17.dp, top = 25.dp, end = 2.dp, bottom = 2.dp),
        ) {
            //Fifth Card
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.White)
                    .width(160.dp)
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.dddd),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    Text(
                        text = "Modern Table Lamp", style = TextStyle(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        )
                    )
                    Text(text = "Rs.2765", color = Color.Gray)
                }

            }
            Spacer(modifier = Modifier.width(35.dp))

            // Sixth Card
            Card(
                modifier = Modifier
                    .clip(RoundedCornerShape(15.dp))
                    .background(color = Color.White)
                    .width(160.dp)
                    .height(160.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.imgs),
                        contentDescription = null,
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                    )
                    Text(
                        text = "Toothbrush Holder", style = TextStyle(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        )
                    )
                    Text(text = "Rs.350", color = Color.Gray)
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListViewBody(){
    ListViewBody()
}
