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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.effectivemobiletest.R
import com.example.effectivemobiletest.ui.module.AuthModule
import com.example.effectivemobiletest.ui.module.AuthState
import com.example.effectivemobiletest.ui.styles.StyleTextDefault
import com.example.effectivemobiletest.ui.theme.AddictiveTextColor
import com.example.effectivemobiletest.ui.theme.HyperLinkTextColor
import com.example.effectivemobiletest.ui.theme.PlaceHolderTextColor
import com.example.effectivemobiletest.ui.theme.PrimaryButtonColor

class LogInActivity : ComponentActivity() {
    private lateinit var authModule: AuthModule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                            text = "Welcome back",
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
                            LogInFields()

                            Spacer(Modifier.height(15.dp))

                            ToSignUp()
                        }
                    }
                }
            }
        }
    }

    private fun LogIn(firstName: String){
        val res = authModule.login(firstName)

        when(res){
            AuthState.NotFound -> {
                Toast.makeText(
                    this@LogInActivity,
                    "Not found account",
                    Toast.LENGTH_LONG
                ).show()
            }
            is AuthState.Success -> {
                Toast.makeText(
                    this@LogInActivity,
                    "Log in successful",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(Intent(this@LogInActivity, MainActivity::class.java))
            }
        }
    }

    @Composable
    fun ToSignUp(){
        Box(modifier = Modifier
            .fillMaxWidth()
        ){
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "Don't have account?",
                style = StyleTextDefault(8.sp, color = AddictiveTextColor)
            )

            ClickableText(
                modifier = Modifier.align(Alignment.Center),
                text = AnnotatedString("Sign up"),
                style = StyleTextDefault(10.sp, color = HyperLinkTextColor),
                onClick = {
                    startActivity(Intent(this@LogInActivity, SignInActivity::class.java))
                }
            )
        }
    }

    @Composable
    fun LogInFields(){
        var firstNameText by remember { mutableStateOf(TextFieldValue("")) }
        var passwordText by remember { mutableStateOf(TextFieldValue("")) }

        var passwordVisibility by remember { mutableStateOf(false) }

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
            value = passwordText,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation =
                if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
            onValueChange = {
                passwordText = it
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

                if(passwordText.text.isEmpty())Row(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Password",
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

                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick =
                    {
                        passwordVisibility = !passwordVisibility
                    }
                ) {
                    Icon(
                        painter = painterResource(
                            if(passwordVisibility)
                                R.drawable.visibility else R.drawable.visibility_off
                        ),
                        ""
                    )
                }

            }
        }

        Spacer(Modifier.height(100.dp))

        Button(
            modifier = Modifier
                .height(46.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryButtonColor
            ),
            shape = RoundedCornerShape(size = 15.dp),
            onClick = {
                LogIn(firstNameText.text)
            }
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Login",
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
