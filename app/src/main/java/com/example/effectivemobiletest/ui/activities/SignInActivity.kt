package com.example.effectivemobiletest.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.module.AuthModule
import com.example.effectivemobiletest.ui.module.RegState
import com.example.effectivemobiletest.ui.module.RoomModule
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor
import com.example.effectivemobiletest.ui.theme.HyperLinkTextColor
import com.example.effectivemobiletest.ui.theme.PlaceHolderTextColor
import com.example.effectivemobiletest.ui.theme.PrimaryButtonColor
import kotlinx.coroutines.DelicateCoroutinesApi


class SignInActivity : ComponentActivity() {
    lateinit var authModule: AuthModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RoomModule().init(applicationContext).appDatabase
        authModule = AuthModule(applicationContext)

        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Box(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .padding(top = 116.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Sign in",
                            style = StyleTextDefault(26.sp, fontWeight = FontWeight.W900)
                        )

                        Spacer(Modifier.height(36.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    start = 44.dp,
                                    end = 44.dp,
                                    top = 44.dp,
                                    bottom = 0.dp
                                )
                        ) {
                            SignInFields()

                            Spacer(Modifier.height(15.dp))

                            ToLogIn()
                        }

                        SignInWithServices()

                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun signIn(
        firstName: String,
        secondName: String,
        email: String) {

        var regStatus = authModule.registration(
            firstName, secondName, email
        )

        when (regStatus){
            RegState.NotEmail -> {
                Toast.makeText(
                    this@SignInActivity,
                    "Not Correct Email",
                    Toast.LENGTH_LONG
                ).show()
            }
            RegState.NotFull -> {
                Toast.makeText(
                    this@SignInActivity,
                    "Not Full Data",
                    Toast.LENGTH_LONG
                ).show()
            }
            is RegState.Success -> {
                Toast.makeText(
                    this@SignInActivity,
                    "Sign in successful!",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }
            RegState.AlreadyExist -> {
                Toast.makeText(
                    this@SignInActivity,
                    "Account is already exist!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    @Composable
    fun SignInWithServices(){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(R.drawable.google),
                    contentDescription = "Красная звезда"
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Sign in with Google",
                    style = StyleTextDefault(12.sp, fontWeight = FontWeight.W900, align = TextAlign.Center)
                )
            }

            Spacer(Modifier.height(32.dp))

            Row(verticalAlignment = Alignment.CenterVertically){
                Icon(
                    painter = painterResource(R.drawable.apple),
                    contentDescription = "Красная звезда"
                )

                Spacer(Modifier.width(8.dp))

                Text(
                    text = "Sign in with Apple",
                    style = StyleTextDefault(12.sp, fontWeight = FontWeight.W900, align = TextAlign.Center)
                )
            }
        }
    }

    @Composable
    fun ToLogIn(){
        Box(modifier = Modifier
            .fillMaxWidth()
        ){
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "Already have an account?",
                style = StyleTextDefault(8.sp, color = AddictiveTextColor)
            )

            ClickableText(
                modifier = Modifier.align(Alignment.Center),
                text = AnnotatedString("Log in"),
                style = StyleTextDefault(10.sp, color = HyperLinkTextColor),
                onClick = {
                    startActivity(Intent(this@SignInActivity, LogInActivity::class.java))
                }
            )
        }
    }

    @Composable
    fun SignInFields(){
        var firstNameText by remember { mutableStateOf(TextFieldValue("")) }
        var secondNameText by remember { mutableStateOf(TextFieldValue("")) }
        var EmailText by remember { mutableStateOf(TextFieldValue("")) }

        BasicTextField(
            modifier = Modifier.height(30.dp),
            singleLine = true,
            textStyle = StyleTextDefault(11.sp, align = TextAlign.Center),
            value = firstNameText,
            onValueChange = {
                firstNameText = it
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

                if(firstNameText.text.isEmpty())Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "First name",
                        style = StyleTextDefault(
                            11.sp,
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

        }

        Spacer(Modifier.height(35.dp))

        BasicTextField(
            modifier = Modifier.height(30.dp),
            singleLine = true,
            textStyle = StyleTextDefault(11.sp, align = TextAlign.Center),
            value = secondNameText,
            onValueChange = {
                secondNameText = it
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

                if(secondNameText.text.isEmpty())Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Second name",
                        style = StyleTextDefault(
                            11.sp,
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
        }

        Spacer(Modifier.height(35.dp))

        BasicTextField(
            modifier = Modifier.height(30.dp),
            singleLine = true,
            textStyle = StyleTextDefault(11.sp, align = TextAlign.Center),
            value = EmailText,
            onValueChange = {
                EmailText = it
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

                if(EmailText.text.isEmpty())Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Email",
                        style = StyleTextDefault(
                            11.sp,
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
        }

        Spacer(Modifier.height(35.dp))

        Button(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryButtonColor
            ),
            shape = RoundedCornerShape(size = 15.dp),
            onClick = {

                signIn(
                    firstName = firstNameText.text,
                    secondName = secondNameText.text,
                    email = EmailText.text
                )

            }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Sign in",
                style = StyleTextDefault(
                    14.sp,
                    align = TextAlign.Center,
                    fontWeight = FontWeight.W900,
                    color = Color.White
                )
            )
        }
    }
}