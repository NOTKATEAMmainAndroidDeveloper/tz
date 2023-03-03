package com.example.effectivemobiletest.ui.viewmodels.home.home.aboutItem

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.effectivemobiletest.ui.models.json.ItemModelDescriptionJson
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor
import com.example.effectivemobiletest.ui.theme.PrimaryButtonColor
import kotlinx.coroutines.runBlocking
import org.json.JSONObject

class AboutItemView(var context: Context, var isAboutPageOn: MutableState<Boolean>) {
    var API = "https://run.mocky.io/v3/f7f99d04-4971-45d5-92e0-70333383c239"

    var countItem = mutableStateOf(0)

    var model = mutableStateOf(ItemModelDescriptionJson())

    var selectedIndexImage = mutableStateOf(0)

    var scaleImage = mutableStateOf(1f)

    private fun getObject() = runBlocking{
        val request: RequestQueue = Volley.newRequestQueue(context)

        val data = StringRequest(
            Request.Method.GET, API,
            { response ->
                val strResp = response.toString()
                val jsonObj = JSONObject(strResp)

                var newModel = ItemModelDescriptionJson(
                    name = jsonObj.getString("name"),
                    description = jsonObj.getString("description"),
                    rating = jsonObj.getDouble("rating"),
                    numberOfReviews = jsonObj.getInt("number_of_reviews"),
                    price = jsonObj.getInt("price"),
                )

                var imageJson = jsonObj.getJSONArray("image_urls")

                for(index in 0 until imageJson.length()){
                    newModel.imageUrls.add(imageJson.getString(index))
                }

                var colorJson = jsonObj.getJSONArray("colors")

                for(index in 0 until colorJson.length()){
                    newModel.colors.add(colorJson.getString(index))
                }

                model.value = newModel

            }, {

            }
        )

        request.add(data)
        request.start()
    }

    @Composable
    fun Execute(){
        getObject()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(ScrollState(1))
        ) {

            if(model.value.imageUrls.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Box(modifier = Modifier.fillMaxSize()){
                        AsyncImage(
                            modifier = Modifier
                                .height(320.dp)
                                .align(Alignment.TopCenter)
                                .graphicsLayer {
                                    scaleX = scaleImage.value
                                    scaleY = scaleImage.value
                                }
                                .pointerInput(Unit) {
                                    detectTransformGestures() { _: Offset, _: Offset, zoom: Float, _: Float ->
                                        scaleImage.value *= zoom
                                    }
                                },
                            model = model.value.imageUrls[selectedIndexImage.value],
                            contentDescription = null
                        )

                        IconButton(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(12.dp),
                            onClick = { isAboutPageOn.value = false }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.ArrowBack,
                                contentDescription = "",
                                modifier = Modifier.size(16.dp),
                                tint = Color.Black
                            )
                        }

                    }
                }

                Spacer(Modifier.height(24.dp))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    itemsIndexed(model.value.imageUrls){ index, item ->
                        Spacer(Modifier.width(7.dp))

                        AsyncImage(
                            modifier = Modifier
                                .height(if (selectedIndexImage.value == index) 100.dp else 80.dp)
                                .width(if (selectedIndexImage.value == index) 100.dp else 70.dp)
                                .shadow(
                                    12.dp, spotColor = if (selectedIndexImage.value == index)
                                        Color.Black else Color.Transparent
                                )
                                .clickable {
                                    selectedIndexImage.value = index
                                },
                            model = model.value.imageUrls[index],
                            contentDescription = null
                        )

                        Spacer(Modifier.width(7.dp))
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()
                ){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            maxLines = 2,
                            text = model.value.name!!,
                            style = StyleTextDefault(
                                17.sp,
                                fontWeight = FontWeight.W900
                            )
                        )

                        Text(
                            text = model.value.price!!.toString() + " $",
                            style = StyleTextDefault(
                                13.sp,
                                fontWeight = FontWeight.W900
                            )
                        )
                    }

                    Spacer(Modifier.height(13.dp))

                    Text(
                        modifier = Modifier.fillMaxWidth(0.75f),
                        text = model.value.description!!,
                        style = StyleTextDefault(
                            9.sp,
                            color = AddictiveTextColor
                        )
                    )

                    Spacer(Modifier.height(13.dp))

                    Row(){

                        Icon(
                            modifier = Modifier.size(10.dp),
                            imageVector = Icons.Default.Star,
                            contentDescription = "",
                            tint = Color(246, 192, 66)
                        )

                        Spacer(Modifier.width(1.dp))

                        Text(
                            text = model.value.rating!!.toString(),
                            style = StyleTextDefault(
                                9.sp,
                                fontWeight = FontWeight.W900
                            )
                        )

                        Spacer(Modifier.width(4.dp))

                        Text(
                            text = "(" + model.value.numberOfReviews!!.toString() + ")",
                            style = StyleTextDefault(
                                9.sp,
                                color = AddictiveTextColor
                            )
                        )
                    }

                    Spacer(Modifier.height(13.dp))

                    Text(
                        text = "Colors",
                        style = StyleTextDefault(
                            10.sp,
                            color = AddictiveTextColor,
                            fontWeight = FontWeight.W900
                        )
                    )

                    LazyRow(){
                        items(model.value.colors){ color ->
                            var typeColor = android.graphics.Color.valueOf(android.graphics.Color.parseColor(color))

                            Button(
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(typeColor.red(), typeColor.green(), typeColor.blue())
                                ),
                                border = BorderStroke(1.dp, Color.Gray),
                                onClick = {

                                }
                            ){

                            }

                            Spacer(Modifier.width(15.dp))
                        }
                    }

                    Spacer(Modifier.height(20.dp))
                }

                BottomBar()

            }
        }
    }

    @Composable
    fun BottomBar(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(Color(24, 23, 38)),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(){

                Text(
                    text = "Quantity: ${countItem.value}",
                    style = StyleTextDefault(
                        10.sp,
                        color = AddictiveTextColor,
                        fontWeight = FontWeight.W900
                    )
                )

                Spacer(Modifier.height(10.dp))

                Row(){
                    Button(
                        modifier = Modifier
                            .height(22.dp)
                            .width(39.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryButtonColor
                        ),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {if(countItem.value > 0) countItem.value--}
                    ) {
                        Text(
                            text = "-",
                            style = StyleTextDefault(
                                10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W900,
                                align = TextAlign.Center
                            )
                        )
                    }

                    Spacer(Modifier.width(20.dp))

                    Button(
                        modifier = Modifier
                            .height(22.dp)
                            .width(39.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryButtonColor
                        ),
                        contentPadding = PaddingValues(0.dp),
                        onClick = {countItem.value++}
                    ) {
                        Text(
                            text = "+",
                            style = StyleTextDefault(
                                10.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W900,
                                align = TextAlign.Center
                            )
                        )
                    }

                }
            }

            Button(
                modifier = Modifier
                    .height(45.dp)
                    .width(170.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryButtonColor
                ),
                shape = RoundedCornerShape(15.dp),
                onClick = {}
            ) {
                Text(
                    text = "Add to cart",
                    style = StyleTextDefault(
                        10.sp,
                        color = Color.White,
                        fontWeight = FontWeight.W900
                    )
                )
            }
        }
    }
}