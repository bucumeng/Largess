package com.example.largess.screen.home

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.AssistChipDefaults.shape
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.largess.R
import com.example.largess.ui.theme.Poppins
import com.example.largess.ui.theme.secondColor
import androidx.compose.ui.graphics.Shape
import androidx.navigation.NavController


@Composable
fun HomeScreen(modifier: Modifier = Modifier,navController: NavController,onAddClick:() -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val home = painterResource(id = R.drawable.home_24)
    val search = painterResource(id = R.drawable.search_24)
    val profile = painterResource(id = R.drawable.person_24)
    val message = painterResource(id = R.drawable.message_24)
    val add = painterResource(id = R.drawable.add_24)

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = secondColor,
                contentColor = Color.White,
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 5.dp, end = 60.dp),
                        contentAlignment = Alignment.Center,
                    )
                    {
                        Text(text ="Largess",
                            textAlign = TextAlign.Start,
                            fontFamily = Poppins)
                    }
                },
                navigationIcon = {},
                actions = {}
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    TextButton(
                        onClick = { "/TODO/" },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color.White)
                    ) {
                        Text(text = "Anasayfa", color = secondColor, fontFamily = Poppins)
                    }
                    TextButton(
                        onClick = { "/TODO/" },
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color.White)
                    ) {
                        Text(text = "Comunity", color = secondColor, fontFamily = Poppins)
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = secondColor,
                contentColor = Color.White,
                elevation = 2.dp,
                cutoutShape = CircleShape,
                content = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { selectedTab = 0 }) {
                            androidx.compose.material3.Icon(
                                painter = home,
                                contentDescription = "Home"
                            )
                        }
                        IconButton(onClick = { selectedTab = 1 }) {
                            androidx.compose.material3.Icon(
                                painter = search,
                                contentDescription = "Search"
                            )
                        }
                        Spacer(modifier =Modifier.padding(10.dp))
                        IconButton(onClick = { selectedTab = 2 }) {
                            androidx.compose.material3.Icon(
                                painter = message,
                                contentDescription = "Message"
                            )
                        }
                        IconButton(onClick = { selectedTab = 3 }) {
                            androidx.compose.material3.Icon(
                                painter = profile,
                                contentDescription = "Profile"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddClick()
                    navController.navigate("add")  {

                    } },
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .background(Color.White)
            ) {
                androidx.compose.material3.Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        backgroundColor = Color.White,
        contentColor = secondColor
    )
}