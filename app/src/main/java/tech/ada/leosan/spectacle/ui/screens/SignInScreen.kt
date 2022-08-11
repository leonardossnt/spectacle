package tech.ada.leosan.spectacle.ui.screens

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import tech.ada.leosan.spectacle.R

@Preview
@Composable
fun SignInPreview() {
    SignInScreen(
        navigateToHome = {},
        navigateToSignUp = {}
    )
}

@Composable
fun SignInScreen(
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 60.dp)
    ) {
        LoginTitle()

        LoginForm { navigateToHome() }

        Spacer(Modifier.height(16.dp))

        NextAuthScreenButton(stringResource(R.string.signup)) { navigateToSignUp() }
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
fun NextAuthScreenButton(text: String, action: () -> Unit) {
    ClickableText(
        text = AnnotatedString(text),
        onClick = { action() },
        style = TextStyle(
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colors.primaryVariant
        )
    )
}

@Composable
fun LoginForm(navigateToHome: () -> Unit) {
    val context = LocalContext.current

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

    SignInButton(username, password,
        onSignedIn = {
            Toast.makeText(
                context,
                context.getString(R.string.authenticated),
                Toast.LENGTH_SHORT
            ).show()
            navigateToHome()
        },
        onSignInFailed = {
            Toast.makeText(
                context,
                context.getString(R.string.invalid_login_or_password),
                Toast.LENGTH_SHORT
            ).show()
        })
}

@Composable
private fun SignInButton(
    username: String,
    password: String,
    onSignedIn: () -> Unit,
    onSignInFailed: () -> Unit
) {
    Button(onClick = {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            signIn(username, password, onSignedIn, onSignInFailed)
        } else {
            // TODO: User / Password invalid
        }
    }) {
        Text(stringResource(R.string.signin).uppercase())
    }
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
        modifier = Modifier.fillMaxWidth(),

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

private fun signIn(
    email: String,
    password: String,
    onSignedIn: () -> Unit,
    onSignInFailed: () -> Unit
) {
    val auth = Firebase.auth

    auth.signInWithEmailAndPassword(email, password)
        .addOnSuccessListener { authResult ->
            onSignedIn()
        }.addOnFailureListener { exception ->
            onSignInFailed()
            // case 1: bad formatted email -- The email address is badly formatted.
            // zza ERROR_INVALID_EMAIL
            // case 2: invalid user -- There is no user record corresponding to this identifier. The user may have been deleted.
            // zza ERROR_USER_NOT_FOUND
            // case 3: invalid password -- The password is invalid or the user does not have a password.
            // zza ERROR_WRONG_PASSWORD
        }
}