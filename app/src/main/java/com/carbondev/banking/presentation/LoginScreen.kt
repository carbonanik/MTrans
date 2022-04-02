package com.carbondev.banking.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carbondev.banking.R
import com.carbondev.banking.data.dto.LoginResponse
import com.carbondev.banking.data.model.User
import com.carbondev.banking.ui.theme.MTransTheme
import com.carbondev.banking.ui.theme.Purple500
import com.carbondev.banking.ui.theme.Purple700
import com.carbondev.banking.util.Resource
import com.carbondev.banking.util.passwordIsValid
import com.carbondev.banking.util.usernameIsValid

const val LOGIN_WITH_GOOGLE = "Google"
const val LOGIN_WITH_FACEBOOK = "Facebook"
const val LOADING = "Loading..."
const val SUBMIT = "Submit"
const val UPDATE = "Update"
const val SUCCESSFUL = "Successful"

@Preview
@Composable
fun LoginPrev() {
    MTransTheme {
        LoginScreen(
            response = null,
            alertData = null,
            logIn = {username, password ->  },
            goToDashboard = {},
            alertDismiss = {}
        )
    }
}

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel,
    loggedIn: () -> Unit
) {
    LoginScreen(
        response = viewModel.loginResponse,
        alertData = viewModel.alertData,

        goToDashboard = {
            navController.navigate(Screen.DashboardScreen.route)
            viewModel.loginResponse = null
        },
        alertDismiss = { viewModel.alertData = null },
        logIn = { username, password ->
            viewModel.login(username, password)
        }
    )

}

@Composable
fun LoginScreen(
    response: Resource<LoginResponse>?,
    alertData: AlertDialogData?,
    logIn: (username: String, password: String) -> Unit,
    goToDashboard: () -> Unit,
    alertDismiss: () -> Unit
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.mtrans_logo),
                contentDescription = "",
                Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Enter login detail to access your account")

            var usernameError by remember { mutableStateOf(false) }
            var username by remember { mutableStateOf("") }

            var passwordError by remember { mutableStateOf(false) }
            var password by remember { mutableStateOf("") }

            var errorText: String? by remember { mutableStateOf(null) }

            var submitButtonText by remember { mutableStateOf(SUBMIT) }
            var googleLoginButtonText by remember { mutableStateOf(LOGIN_WITH_GOOGLE) }
            var facebookLoginButtonText by remember { mutableStateOf(LOGIN_WITH_FACEBOOK) }

            if (response is Resource.Loading) {
                submitButtonText = LOADING
                errorText = ""
            }

            if (response is Resource.Success) {
                submitButtonText = SUCCESSFUL
                errorText = ""
                goToDashboard()
            }

            if (response is Resource.Error) {
                submitButtonText = SUBMIT
                errorText = "Error: ${response?.errorMessage}"
            }

            /**
             *  Email Field
             */
            OutlineTextFieldWithErrorView(
                value = username, onValueChange = { data: String ->
                    username = data
                    if (username.usernameIsValid()) usernameError = false
                },
                isError = usernameError,
                label = { Text("username") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                errorMsg = "Enter A Valid Username",
                colors = fieldColor(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "",
                        tint = Purple700
                    )
                }
            )

            var passwordVisibility: Boolean by remember { mutableStateOf(false) }

            /**
             *  Password Field
             */
            OutlineTextFieldWithErrorView(
                value = password, onValueChange = { data: String ->
                    password = data
                    if (password.passwordIsValid()) passwordError = false
                },
                isError = passwordError,
                label = { Text("password") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                errorMsg = "Password Too Short",
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24)
//                        if (passwordVisibility) Icons.Default.Email
//                        else painterResource(id = R.drawable.ic_baseline_remove_red_eye_24)

                    IconButton(onClick = {
                        passwordVisibility = !passwordVisibility
                    }) {
                        Icon(painter = image, "", tint = Purple700)
                    }
                },
                colors = fieldColor(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "",
                        tint = Purple700
                    )
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            /**
             * Submit Button
             */
            Button(
                enabled = submitButtonText == SUBMIT,
                onClick = {

                    usernameError = !username.usernameIsValid()
                    passwordError = !password.passwordIsValid()

                    if (!(usernameError || passwordError)) {
                        logIn(username, password)
                    }
                },
                shape = RoundedCornerShape(size = 8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
            ) {
                if (submitButtonText == LOADING) {
                    CircularProgressIndicator(modifier = Modifier.size(30.dp))
                }
                Text(
                    text = submitButtonText,
                    fontSize = 16.sp
                )
            }

            /**
             * Error Text Under Submit Button
             */
            errorText?.let {
                Row {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(it, fontSize = 14.sp, color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Divider(
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
                Text(text = "Or", fontSize = 18.sp, modifier = Modifier.padding(horizontal = 16.dp))
                Divider(
                    Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row {

                /**
                 * FB login
                 */
                Button(
                    enabled = facebookLoginButtonText == LOGIN_WITH_FACEBOOK,
                    onClick = {

                    },
                    shape = RoundedCornerShape(size = 22.5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                ) {
                    if (facebookLoginButtonText == LOADING) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp))
                    }
                    Text(
                        text = facebookLoginButtonText,
                        fontSize = 16.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(20.dp))

                /**
                 * Google Login Button
                 */
                Button(
                    enabled = googleLoginButtonText == LOGIN_WITH_GOOGLE,
                    onClick = {

                    },
                    shape = RoundedCornerShape(size = 22.5.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .weight(1f),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
                ) {
                    if (googleLoginButtonText == LOADING) {
                        CircularProgressIndicator(modifier = Modifier.size(30.dp))
                    }
                    Text(
                        text = googleLoginButtonText,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }
            }

            Spacer(modifier = Modifier.height(80.dp))


//            Row {
//                Text(text = "Don't have an account?")
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = "Sign Up",
//                    color = Purple500,
//                    fontWeight = Bold,
//                    modifier = Modifier.clickable {
//                        navController.navigate("signup")
//                    })
//            }
        }
        alertData?.let { data ->
            AlertDialog(
                onDismissRequest = {
                },
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = data.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(data.body, fontSize = 16.sp)
                    }
                },
                backgroundColor = Color.Red,
                contentColor = Color.White,
                buttons = {
                    Button(onClick = {
                        alertDismiss()
                    }, Modifier.fillMaxWidth()) {
                        Text(text = "Dismiss", textAlign = TextAlign.Center)
                    }
                },
            )
        }

    }
}


@Composable
fun fieldColor(): TextFieldColors {
    return TextFieldDefaults.outlinedTextFieldColors(
        focusedBorderColor = Purple500,
        unfocusedBorderColor = Purple500,
        focusedLabelColor = Purple500,
        unfocusedLabelColor = Purple500
    )
}