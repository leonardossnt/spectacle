package tech.ada.leosan.spectacle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginActivity : ComponentActivity() {
    val TAG: String = "LoginActivity"
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContent {
            LoginScreen()
        }
    }

    @Composable
    fun LoginScreen() {
        MaterialTheme(
            colors = CustomColors.Colors
        ) {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                Background()
                LoginTitle()
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    LoginForm()
                }
            }
        }
    }

    @Composable
    fun LoginTitle() {
        Box(
            modifier = Modifier.padding(vertical = 128.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                getString(R.string.app_name).uppercase(),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }

    @Composable
    fun LoginForm() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }

        CustomTextFieldForLogin(
            value = username,
            label = getString(R.string.username),
            onValueChange = { username = it }
        )

        Spacer(Modifier.height(16.dp))

        CustomTextFieldForLogin(
            value = password,
            label = getString(R.string.password),
            onValueChange = { password = it },
            isPasswordField = true
        )

        Spacer(Modifier.height(32.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(getString(R.string.signin).uppercase())
        }

        Spacer(Modifier.height(16.dp))

        ClickableText(
            text = AnnotatedString(getString(R.string.signup)),
            onClick = { /*TODO*/ },
            style = TextStyle(
                textDecoration = TextDecoration.Underline,
                color = MaterialTheme.colors.primaryVariant
            )
        )
    }

    @Composable
    fun CustomTextFieldForLogin(
        value: String,
        label: String,
        onValueChange: (String) -> Unit,
        isPasswordField: Boolean = false
    ) {
        var isPasswordVisible by remember { mutableStateOf(false) }

        TextField(
            value = value,
            label = { Text(label) } ,
            onValueChange = onValueChange,

            // layout
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                cursorColor = Color.Black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(50.dp),
            singleLine = true,

            // password
            visualTransformation =
                if (!isPasswordField) VisualTransformation.None
                else if (isPasswordVisible) VisualTransformation.None
                    else PasswordVisualTransformation(),
            trailingIcon = {
                if (isPasswordField) {
                    val image = if (isPasswordVisible) Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (isPasswordVisible) getString(R.string.hide_password)
                    else getString(R.string.show_password)

                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ) {
                        Icon(imageVector = image, description)
                    }
                }
            }
        )
    }

}