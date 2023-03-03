package com.example.effectivemobiletest.ui.activities

import android.content.res.Resources
import android.graphics.drawable.VectorDrawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.activities.ui.theme.EffectiveMobileTestTheme
import com.example.effectivemobiletest.ui.theme.CustomIconDrawable
import com.example.effectivemobiletest.ui.theme.PrimaryButtonColor
import com.example.effectivemobiletest.ui.viewmodels.home.home.HomeViewModel
import com.example.effectivemobiletest.ui.viewmodels.home.profile.ProfileViewModel


data class MenuPoint(
    var icon: ImageVector,
    var content: @Composable () -> Unit
)

class MainActivity : ComponentActivity() {
    private var selectedMenuIndex = mutableStateOf(0)

    private val profileViewModel: ProfileViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    private var menuPoints = arrayOf(
        MenuPoint(Icons.Outlined.Home){homeViewModel.ui.Execute()},
        MenuPoint(Icons.Outlined.Favorite){Text("gewgwegwegwe")},
        MenuPoint(Icons.Outlined.ShoppingCart){},
        MenuPoint(CustomIconDrawable.DialogOutlined){},
        MenuPoint(Icons.Outlined.Person){profileViewModel.ui.Execute()}
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        profileViewModel.init(this)
        homeViewModel.init(this)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                ){

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 65.dp)
                    ){
                        menuPoints[selectedMenuIndex.value].content()
                    }

                    Row(modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                    ){
                        BottomMenu()
                    }

                }
            }
        }
    }

    @Composable
    fun BottomMenu(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ){
            for((index, point) in menuPoints.withIndex()){
                IconButton(
                    onClick = {
                        selectedMenuIndex.value = index
                    }
                ) {
                    Icon(
                        tint = if(selectedMenuIndex.value == index) PrimaryButtonColor else Color.Black,
                        imageVector = point.icon,
                        contentDescription = ""
                    )
                }
            }
        }
    }

}