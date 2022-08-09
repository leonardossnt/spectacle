package tech.ada.leosan.spectacle

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}

@Composable
fun LoginScreen(navController: NavController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LoginTitle()
        LoginForm()
    }
}

@Composable
fun LoginTitle() {
    Box(
        modifier = Modifier.padding(bottom = 64.dp),
    ) {
        Text(
            stringResource(R.string.app_name).uppercase(),
            style = TextStyle(
                color = Color.White,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
        )
    }
}

@Composable
fun LoginForm() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    CustomTextFieldForLogin(
        value = username,
        label = stringResource(R.string.username),
        onValueChange = { username = it }
    )

    Spacer(Modifier.height(16.dp))

    CustomTextFieldForLogin(
        value = password,
        label = stringResource(R.string.password),
        onValueChange = { password = it },
        isPasswordField = true
    )

    Spacer(Modifier.height(32.dp))

    Button(onClick = { /*TODO*/ }) {
        Text(stringResource(R.string.signin).uppercase())
    }

    Spacer(Modifier.height(16.dp))

    ClickableText(
        text = AnnotatedString(stringResource(R.string.signup)),
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
        label = { Text(label) },
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

                val description = if (isPasswordVisible) stringResource(R.string.hide_password)
                else stringResource(R.string.show_password)

                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(imageVector = image, description)
                }
            }
        }
    )
}