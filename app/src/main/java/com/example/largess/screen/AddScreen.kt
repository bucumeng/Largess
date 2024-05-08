package com.example.largess.screen

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Build.VERSION_CODES.R
import android.provider.MediaStore
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.largess.ui.theme.secondColor
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.largess.ui.theme.Poppins
import com.example.largess.ui.theme.ReemKufi
import java.io.IOException


@Composable
fun AddScreen(navController: NavController, onAddClick: () -> Unit, onAddPhotoClick: () -> Unit,) {
    val content = LocalContext.current
    val img: Bitmap =
        BitmapFactory.decodeResource(Resources.getSystem(), android.R.drawable.ic_menu_report_image)
    val context = LocalContext.current
    val bitmap = remember { mutableStateOf(img) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
    ) {
        if (it != null) {
            bitmap.value = it
        }
    }

    val launchImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        if (Build.VERSION.SDK_INT < 28) {
            bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
        } else {
            val source = it?.let { it1 ->
                ImageDecoder.createSource(context.contentResolver, it1)
            }
            bitmap.value = source?.let { it1 ->
                ImageDecoder.decodeBitmap(it1)
            }!!
        }
    }

    Column(
        modifier = Modifier
            .padding(top = 100.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            border = BorderStroke(3.dp, Color.White),
            elevation = 8.dp,
            backgroundColor = Color.White,
            modifier = Modifier
                .width(250.dp)
                .height(250.dp)
        ) {
            Image(
                bitmap = bitmap.value.asImageBitmap(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(250.dp)
                    .clip(RectangleShape)
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RectangleShape
                    )
            )
        }

        Box(
            modifier = Modifier.padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = com.example.largess.R.drawable.add_24),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White)
                    .size(50.dp)
                    .clickable {
                        onAddPhotoClick() // Call the onAddPhotoClick function first
                        navController.navigate("addPhoto") {
                            popUpTo("add") { inclusive = true } // Ensure clean navigation
                        }
                    }
            )
        }

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            var text by remember { mutableStateOf("") }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {

                    Text(
                        text = "Ürün Adı",
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Start,
                        fontFamily = ReemKufi
                    )

                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("Metni Girin", fontSize = 12.sp, color = Color.Black)}, // Increase font size for label
                        modifier = Modifier
                            .width(100.dp)
                            .padding(4.dp) // Add padding for better visibility
                    )
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {

            var control by remember { mutableStateOf(false) }
            val categoryList = listOf("Etek", "Elbise", "Pantolon", "Şort", "Tshirt", "Sweatshirt", "Ayakkabı", "Bot", "Çanta", "Aksesuar", "Mont", "Şapka")
            var categoryIndex by remember { mutableStateOf(0) }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {

                    Text(
                        text = "Kategori",
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Start,
                        fontFamily = ReemKufi
                    )

                    Box {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { control = true }
                        )
                        {
                            Text(text = categoryList[categoryIndex])
                            Image(
                                painter = painterResource(id = com.example.largess.R.drawable.baseline_arrow_drop_down_24),
                                contentDescription = " "
                            )
                        }

                        DropdownMenu(
                            expanded = control,
                            onDismissRequest = { control = false },
                            modifier = Modifier
                                .width(100.dp) // Make DropdownMenu fill the width
                                .heightIn(
                                    min = 0.dp,
                                    max = 150.dp
                                ) // Set minimum and maximum height for scrollable area
                        ) {
                            Column(modifier = Modifier.fillMaxHeight()) { // Wrap content with Column for scrolling
                                categoryList.forEachIndexed { index, s ->
                                    DropdownMenuItem(text = { Text(text = s) }, onClick = {
                                        Log.d("menü", "Tıklanan Renk: $s ")
                                        categoryIndex = index
                                        control = false
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {

            var control by remember { mutableStateOf(false) }
            val colorList = listOf("Siyah", "Kahverengi", "Kırmızı", "Yeşil", "Gri", "Mavi", "Sarı", "Beyaz", "Turuncu", "Mor", "Lacivert", "Pembe")
            var colorIndex by remember { mutableStateOf(0) }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {

                    Text(
                        text = "Renk",
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Start,
                        fontFamily = ReemKufi
                    )

                    Box {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { control = true }
                        )
                        {
                            Text(text = colorList[colorIndex])
                            Image(
                                painter = painterResource(id = com.example.largess.R.drawable.baseline_arrow_drop_down_24),
                                contentDescription = " "
                            )
                        }

                        DropdownMenu(
                            expanded = control,
                            onDismissRequest = { control = false },
                            modifier = Modifier
                                .width(100.dp) // Make DropdownMenu fill the width
                                .heightIn(
                                    min = 0.dp,
                                    max = 150.dp
                                ) // Set minimum and maximum height for scrollable area
                        ) {
                            Column(modifier = Modifier.fillMaxHeight()) { // Wrap content with Column for scrolling
                                colorList.forEachIndexed { index, s ->
                                    DropdownMenuItem(text = { Text(text = s) }, onClick = {
                                        Log.d("menü", "Tıklanan Ülke: $s ")
                                        colorIndex = index
                                        control = false
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth(0.7f)) {

            var control by remember { mutableStateOf(false) }
            val sizeList = listOf("XS","S","M","L","XL","22","24","26","28","30","32","34","36","38","40","42","44","46","48")
            var sizeIndex by remember { mutableStateOf(0) }

            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(8.dp)
                ) {

                    Text(
                        text = "Beden",
                        modifier = Modifier
                            .weight(1f)
                            .padding(5.dp),
                        textAlign = TextAlign.Start,
                        fontFamily = ReemKufi
                    )

                    Box {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable { control = true }
                        )
                        {
                            Text(text = sizeList[sizeIndex])
                            Image(
                                painter = painterResource(id = com.example.largess.R.drawable.baseline_arrow_drop_down_24),
                                contentDescription = " "
                            )
                        }

                        DropdownMenu(
                            expanded = control,
                            onDismissRequest = { control = false },
                            modifier = Modifier
                                .width(100.dp) // Make DropdownMenu fill the width
                                .heightIn(
                                    min = 0.dp,
                                    max = 150.dp
                                ) // Set minimum and maximum height for scrollable area
                        ) {
                            Column(modifier = Modifier.fillMaxHeight()) { // Wrap content with Column for scrolling
                                sizeList.forEachIndexed { index, s ->
                                    DropdownMenuItem(text = { Text(text = s) }, onClick = {
                                        Log.d("menü", "Tıklanan Ülke: $s ")
                                        sizeIndex = index
                                        control = false
                                    })
                                }
                            }
                        }
                    }
                }
            }
        }
        }
    }



