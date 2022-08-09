package tech.ada.leosan.spectacle

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen(navigateToHome = {}, navigateToSignIn = {})
}

@Composable
fun SignUpScreen(
    navigateToHome: () -> Unit,
    navigateToSignIn: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        LoginTitle()

        SignUpForm { navigateToHome() }

        Spacer(Modifier.height(16.dp))

        NextAuthScreenButton(stringResource(R.string.already_has_login)) { navigateToSignIn() }
    }
}

@Composable
fun SignUpForm(navigateToHome: () -> Unit) {
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

    SignUpButton(username, password,
        onSignUpSuccess = {
            Toast.makeText(
                context,
                context.getString(R.string.authenticated),
                Toast.LENGTH_SHORT
            ).show()
            navigateToHome()
        },
        onSignUpFailed = {
            Toast.makeText(
                context,
                context.getString(R.string.invalid_login_or_password),
                Toast.LENGTH_SHORT
            ).show()
        })
}

@Composable
private fun SignUpButton(
    username: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailed: () -> Unit
) {
    Button(onClick = {
        if (username.isNotEmpty() && password.isNotEmpty()) {
            signUp(username, password, onSignUpSuccess, onSignUpFailed)
        } else {
            // TODO: User / Password invalid
        }
    }) {
        Text(stringResource(R.string.signup).uppercase())
    }
}

fun signUp(
    email: String,
    password: String,
    onSignUpSuccess: () -> Unit,
    onSignUpFailed: () -> Unit
) {
    val auth = Firebase.auth

    auth.createUserWithEmailAndPassword(email, password)
        .addOnSuccessListener { authResult ->
            println("User created successfully: ${authResult.user} ${authResult.additionalUserInfo} ${authResult.credential}")
            onSignUpSuccess()
        }.addOnFailureListener { exception ->
            println("Sign up failed: ${exception.message}")
            onSignUpFailed()
            // case 1: bad formatted email -- The email address is badly formatted.
            // case 2: username already taken - The email address is already in use by another account
            // case 3: invalid password -- The password is invalid or the user does not have a password.
        }
}