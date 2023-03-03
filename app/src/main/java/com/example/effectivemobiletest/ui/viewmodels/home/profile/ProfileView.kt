package com.example.effectivemobiletest.ui.viewmodels.home.profile

import android.widget.Button
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor
import com.example.effectivemobiletest.ui.theme.CustomIconDrawable.Companion.AutoRenew
import com.example.effectivemobiletest.ui.theme.CustomIconDrawable.Companion.CardIcon
import com.example.effectivemobiletest.ui.theme.CustomIconDrawable.Companion.HelpIcon
import com.example.effectivemobiletest.ui.theme.CustomIconDrawable.Companion.LogOutIcon
import com.example.effectivemobiletest.ui.theme.CustomIconDrawable.Companion.RightArrow
import com.example.effectivemobiletest.ui.theme.IconBackLightGrey
import com.example.effectivemobiletest.ui.theme.PrimaryButtonColor

class ProfileView(private var viewModel: ProfileViewModel) {
    @Composable
    fun Execute(){
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(ScrollState(1), enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "Profile",
                style = StyleTextDefault(15.sp, fontWeight = FontWeight.W900)
            )

            Spacer(modifier = Modifier.height(19.dp))

            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.google),
                contentDescription = "",
                modifier = Modifier
                    .size(61.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Change photo",
                style = StyleTextDefault(8.sp, fontWeight = FontWeight.W900, color = AddictiveTextColor)
            )

            Spacer(modifier = Modifier.height(17.dp))

            Text(
                text = "Satria Adhi Pradana",
                style = StyleTextDefault(15.sp, fontWeight = FontWeight.W900)
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 43.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryButtonColor
                ),
                shape = RoundedCornerShape(size = 15.dp),
                onClick = {

                }
            ) {
                Icon(Icons.Outlined.KeyboardArrowUp, "")

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Upload item",
                    style = StyleTextDefault(
                        14.sp,
                        align = TextAlign.Center,
                        fontWeight = FontWeight.W900,
                        color = Color.White
                    )
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Column(Modifier.padding(horizontal = 32.dp)) {
                CustomMenuButton(
                    title = "Trade store",
                    icon = CardIcon
                )

                CustomMenuButton(
                    title = "Payment method",
                    icon = CardIcon
                )

                CustomMenuButton(
                    title = "Balance",
                    icon = CardIcon,
                    isForwardEnabled = false,
                    forwardText = "$ 1593"
                )

                CustomMenuButton(
                    title = "Trade history",
                    icon = CardIcon
                )

                CustomMenuButton(
                    title = "Restore Purchase",
                    icon = AutoRenew
                )

                CustomMenuButton(
                    title = "Help",
                    icon = HelpIcon,
                    isForwardEnabled = false
                )

                CustomMenuButton(
                    title = "Log out",
                    icon = LogOutIcon,
                    isForwardEnabled = false
                ){
                    viewModel.logOut()
                }

            }
        }
    }

    @Composable
    fun CustomMenuButton(
        icon: ImageVector = RightArrow,
        title: String = "fweefw",
        forwardText: String = "",
        isForwardEnabled: Boolean = true,
        isHaveSpacer: Boolean = true,
        onClick: () -> Unit = {}
    ){
        Button(
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp),
            onClick = onClick,
        ) {
            Row(
                modifier = Modifier
                    .size(40.dp)
                    .height(40.dp)
                    .width(40.dp)
                    .clip(CircleShape)
                    .background(IconBackLightGrey),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    imageVector = icon,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(24.dp)
                )
            }

            Spacer(Modifier.width(6.dp))

            Text(
                text = title,
                style = StyleTextDefault(
                    14.sp,
                    align = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    color = Color.Black
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = forwardText,
                    style = StyleTextDefault(
                        14.sp,
                        align = TextAlign.Center,
                        fontWeight = FontWeight.W900,
                        color = Color.Black
                    )
                )
                if(isForwardEnabled)Icon(
                    imageVector = RightArrow,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
        if(isHaveSpacer)Spacer(modifier = Modifier.height(16.dp))
    }
}