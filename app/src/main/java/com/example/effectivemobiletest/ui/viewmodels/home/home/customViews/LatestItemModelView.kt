package com.example.effectivemobiletest.ui.viewmodels.home.home.customViews

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.models.json.ItemModelJson
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor

class LatestItemModelView() {

    @Composable
    fun Execute(item: ItemModelJson){
        Box(
            modifier = Modifier
                .width(114.dp)
                .height(149.dp)
                .clip(RoundedCornerShape(12.dp)),
        ){
            SubcomposeAsyncImage(
                modifier = Modifier
                    .width(114.dp)
                    .height(149.dp),
                model = item.imageUrl,
                loading = {
                    CircularProgressIndicator()
                },
                contentDescription = "",
                contentScale = ContentScale.FillHeight
            )
            
            Column(
                Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(Color(0, 0, 0, 100))
            ) {

                Spacer(modifier = Modifier.height(3.dp))

                Button(
                    modifier = Modifier
                        .padding(start = 7.dp)
                        .width(35.dp)
                        .height(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(196, 196, 196, 217)
                    ),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {

                    }
                ) {
                    Text(text = item.category!!,
                        style = StyleTextDefault(
                            6.sp,
                            color = Color.Black,
                            align = TextAlign.Center
                        )
                    )
                }


                Text(
                    modifier = Modifier
                        .padding(horizontal = 7.dp),
                    maxLines = 2,
                    text = item.name!!,
                    style = StyleTextDefault(
                        9.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W900
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    Text(
                        modifier = Modifier
                            .padding(start = 7.dp, bottom = 7.dp, top = 14.dp),
                        text = "$ " + item.price.toString(),
                        style = StyleTextDefault(
                            7.sp,
                            color = Color.White
                        )
                    )

                    IconButton(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(20.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = AddictiveTextColor
                        ),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            modifier = Modifier
                                .size(12.dp),
                            contentDescription = ""
                        )
                    }
                }
            }
            
        }
    }
}