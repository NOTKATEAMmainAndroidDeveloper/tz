package com.example.effectivemobiletest.ui.viewmodels.home.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor
import com.example.effectivemobiletest.ui.theme.IconBackLightGrey
import com.example.effectivemobiletest.ui.theme.PlaceHolderTextColor
import com.example.effectivemobiletest.ui.viewmodels.home.home.aboutItem.AboutItemView
import com.example.effectivemobiletest.ui.viewmodels.home.home.customViews.LatestItemModelView
import com.example.effectivemobiletest.ui.viewmodels.home.home.customViews.SalesItemModelView

class HomeView() {
    var searchString = mutableStateOf("")

    lateinit var viewModel: HomeViewModel

    var isAboutPageOn = mutableStateOf(false)

    constructor(viewModel: HomeViewModel) : this() {
        this@HomeView.viewModel = viewModel
    }

    @Composable
    fun Execute() {
        if(isAboutPageOn.value)
            AboutItemView(viewModel.context, isAboutPageOn).Execute()
        else Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(ScrollState(12), enabled = true)
        ) {
            TopBar()

            Spacer(Modifier.height(9.dp))

            SearchItemBar()

            Spacer(Modifier.height(17.dp))

            CategoryView()

            Spacer(Modifier.height(29.dp))

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 11.dp)){
                if(viewModel.isGet.value)LatestItemView()

                Spacer(Modifier.height(17.dp))

                if(viewModel.isGet.value)FlashSaleView()

                Spacer(Modifier.height(17.dp))

                if(viewModel.isGet.value)BrandsView()
            }
        }
    }

    @Preview
    @Composable
    fun TopBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 7.dp, start = 11.dp, end = 11.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Outlined.Menu, "")
            Text(
                text = buildAnnotatedString {
                    append(
                        AnnotatedString("Trade by ")
                    )
                    append(
                        AnnotatedString("bata", spanStyle = SpanStyle(color = Color(78, 85, 215)))
                    )
                },
                style = StyleTextDefault(20.sp, color = Color.Black, fontWeight = FontWeight.W900)
            )
            Column(){
                Image(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(31.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(1.dp, Color.Gray, CircleShape)
                )
            }
        }

        Spacer(Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Location",
                style = StyleTextDefault(
                    10.sp,
                    align = TextAlign.Center,
                    color = AddictiveTextColor
                )
            )

            Spacer(Modifier.width(6.dp))
        }

    }

    @Preview
    @Composable
    fun SearchItemBar() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 57.dp)
        ){
            var searchStringText by remember {
                mutableStateOf(TextFieldValue(""))
            }

            BasicTextField(
                modifier = Modifier.height(24.dp),
                singleLine = true,
                textStyle = StyleTextDefault(9.sp, align = TextAlign.Center),
                value = searchStringText,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    searchString.value = it.text
                    searchStringText = it
                }
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(232, 232, 232),
                            shape = RoundedCornerShape(size = 15.dp)
                        ),
                ) {

                    if(searchStringText.text.isEmpty())Row(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "What are you looking for ?",
                            style = StyleTextDefault(
                                9.sp,
                                align = TextAlign.Center,
                                color = PlaceHolderTextColor
                            )
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.Center),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        it()
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(Icons.Outlined.Search, "", modifier = Modifier.size(12.dp))
                    Spacer(Modifier.width(16.dp))
                }
            }
        }

    }

    @Preview
    @Composable
    fun CategoryView(){
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ){
            items(viewModel.allCategory){
                Spacer(Modifier.width(15.dp))

                Column(
                    modifier = Modifier
                        .width(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){

                    IconButton(
                        modifier = Modifier
                            .height(42.dp)
                            .width(38.dp)
                            .clip(CircleShape)
                            .background(IconBackLightGrey),
                        onClick = {

                        }
                    ) {
                        Icon(
                            tint = Color.Black,
                            imageVector = ImageVector.vectorResource(it.iconResource),
                            contentDescription = ""
                        )
                    }

                    Spacer(Modifier.height(10.dp))

                    Text(
                        text = it.title,
                        style = StyleTextDefault(
                            8.sp,
                            color = AddictiveTextColor,
                            align = TextAlign.Center
                        )
                    )
                }
            }
        }
    }

    @Preview
    @Composable
    fun LatestItemView(){
        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Latest",
                    style = StyleTextDefault(15.sp, color = Color.Black)
                )

                ClickableText(
                    text = AnnotatedString("View all"),
                    style = StyleTextDefault(9.sp, color = AddictiveTextColor),
                    onClick = {

                    }
                )
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                var itemView = LatestItemModelView()

                items(viewModel.latestItemList){
                    itemView.Execute(item = it)
                    Spacer(Modifier.width(12.dp))
                }
            }
        }
    }

    @Preview
    @Composable
    fun FlashSaleView(){
        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Flash sale",
                    style = StyleTextDefault(15.sp, color = Color.Black)
                )

                ClickableText(
                    text = AnnotatedString("View all"),
                    style = StyleTextDefault(9.sp, color = AddictiveTextColor),
                    onClick = {

                    }
                )
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                var itemView = SalesItemModelView()

                items(viewModel.salesItemList){
                    itemView.Execute(item = it)
                    {
                        isAboutPageOn.value = true
                    }

                    Spacer(Modifier.width(9.dp))
                }
            }
        }
    }

    @Preview
    @Composable
    fun BrandsView(){
        Column(modifier = Modifier
            .fillMaxWidth()
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "Brands",
                    style = StyleTextDefault(15.sp, color = Color.Black)
                )

                ClickableText(
                    text = AnnotatedString("View all"),
                    style = StyleTextDefault(9.sp, color = AddictiveTextColor),
                    onClick = {

                    }
                )
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                var itemView = LatestItemModelView()

                items(viewModel.latestItemList){
                    itemView.Execute(item = it)
                    Spacer(Modifier.width(12.dp))
                }
            }
        }
    }
}