package com.example.effectivemobiletest.ui.viewmodels.home.home.customViews

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.effectivemobiletest.ui.models.json.ItemModelJson
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor

class SalesItemModelView {

    @Composable
    fun Execute(item: ItemModelJson, onClick: (ItemModelJson) -> Unit){
        Box(
            modifier = Modifier
                .width(174.dp)
                .height(221.dp)
                .clip(RoundedCornerShape(12.dp))
                .clickable { onClick(item) }
        ){
            SubcomposeAsyncImage(
                modifier = Modifier
                    .width(174.dp)
                    .height(221.dp),
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

                Spacer(modifier = Modifier.height(4.dp))
                
                Button(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .width(50.dp)
                        .height(17.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(196, 196, 196, 217)
                    ),
                    contentPadding = PaddingValues(0.dp),
                    onClick = {

                    }
                ) {
                    Text(text = item.category!!,
                        style = StyleTextDefault(
                            9.sp,
                            color = Color.Black,
                            align = TextAlign.Center
                        )
                    )
                }

                Text(
                    modifier = Modifier
                        .padding(start = 11.dp, end = 11.dp, top = 10.dp),
                    maxLines = 2,
                    text = item.name!!,
                    style = StyleTextDefault(
                        13.sp,
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
                            .padding(start = 10.dp, bottom = 17.dp),
                        text = "$ " + item.price.toString(),
                        style = StyleTextDefault(
                            10.sp,
                            color = Color.White
                        )
                    )

                    Row(
                        modifier = Modifier
                            .padding(end = 4.dp, bottom = 7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        IconButton(
                            modifier = Modifier
                                .size(28.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = AddictiveTextColor
                            ),
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                modifier = Modifier
                                    .size(12.dp),
                                contentDescription = ""
                            )
                        }

                        Spacer(Modifier.width(5.dp))

                        IconButton(
                            modifier = Modifier
                                .size(35.dp),
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = AddictiveTextColor
                            ),
                            onClick = {

                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                modifier = Modifier
                                    .size(26.dp),
                                contentDescription = ""
                            )
                        }
                    }
                }
            }

        }
    }
}