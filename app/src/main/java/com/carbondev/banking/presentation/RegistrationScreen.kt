package com.carbondev.banking.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.carbondev.banking.ui.custom_view.ComposeMenu
import com.carbondev.banking.ui.theme.MTransTheme
import com.carbondev.banking.ui.theme.Purple700
import com.carbondev.banking.util.*


@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel,
) {
    RegistrationScreen(
        name = viewModel.name,
        updateName = { data: String ->
            if (data.length <= 100) viewModel.name = data
            if (data.nameIsValid()) viewModel.nameError = false
        },
        nameError = viewModel.nameError,

        username = viewModel.username,
        updateUsername = { data: String ->
            viewModel.username = data
            if (viewModel.username.usernameIsValid()) viewModel.usernameError =
                false
        },
        usernameError = viewModel.usernameError,

        email = viewModel.email,
        updateEmail = { data: String ->
            viewModel.email = data
            if (viewModel.email.emailIsValid()) viewModel.emailError = false
        },
        emailError = viewModel.emailError,

        phone = viewModel.phone,
        updatePhone = { data: String ->
            if (data.length <= 20) viewModel.phone = data
            if (viewModel.phone.phoneIsValid()) viewModel.phoneError = false
        },
        phoneError = viewModel.phoneError,

        password = viewModel.password,
        updatePassword = { data: String ->
            viewModel.password = data
            if (viewModel.password.passwordIsValid()) viewModel.passwordError =
                false
        },
        passwordError = viewModel.passwordError,


        referId = viewModel.referId,
        updateReferId = { data: String ->
            viewModel.referId = data
            if (viewModel.referId.referIdIsValid()) viewModel.referIdError = false
        },
        referIdError = viewModel.referIdError,

        goSearchReferScreen = {
            navController.navigate(Screen.SearchReferScreen.route)
        },
        submitButtonText = viewModel.submitButtonText,
        submitButtonClick = {
            viewModel.formValidate()
        },
        errorText = viewModel.errorText,
        alertData = viewModel.alertData,
        alertDialogDismiss = {
            viewModel.alertData = null
        },
        updateSubscriptionType = { type ->
            viewModel.subscriptionType = type
        }
    )
}

@Composable
fun RegistrationScreen(
    name: String,
    updateName: (name: String) -> Unit,
    nameError: Boolean,

    username: String,
    updateUsername: (name: String) -> Unit,
    usernameError: Boolean,


    email: String,
    updateEmail: (name: String) -> Unit,
    emailError: Boolean,


    phone: String,
    updatePhone: (name: String) -> Unit,
    phoneError: Boolean,


    password: String,
    updatePassword: (name: String) -> Unit,
    passwordError: Boolean,

    referId: String,
    updateReferId: (name: String) -> Unit,
    referIdError: Boolean,

    goSearchReferScreen: () -> Unit,
    submitButtonText: String,
    submitButtonClick: () -> Unit,

    errorText: String?,
    alertData: AlertDialogData?,
    alertDialogDismiss: () -> Unit,
    updateSubscriptionType: (type: Int) -> Unit

) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column {
//            TopAppBar(
//                Modifier
//                    .fillMaxWidth()
//                    .height(60.dp),
//                backgroundColor = Color.White,
//            ) {
//
//                Text(
//                    text = "BINGEID",
//                    modifier = Modifier
//                        .padding(4.dp)
//                        .fillMaxWidth(),
//                    fontSize = 28.sp,
//                    color = Purple500,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                )
//            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
//                Spacer(modifier = Modifier.height(40.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.mtrans_logo),
                    contentDescription = "",
                    Modifier.width(100.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Create a new account")

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {


                    OutlineTextFieldWithErrorView(
                        value = name, onValueChange = updateName,
                        isError = nameError,
                        label = { Text("name") },
                        modifier = Modifier.fillMaxWidth(),
                        errorMsg = "Name Is Empty",
                        colors = fieldColor(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                tint = Purple700
                            )
                        }
                    )

                    OutlineTextFieldWithErrorView(
                        value = username, onValueChange = updateUsername,
                        isError = usernameError,
                        label = { Text("username") },
                        modifier = Modifier.fillMaxWidth(),
                        errorMsg = "Name Is Empty",
                        colors = fieldColor(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                tint = Purple700
                            )
                        }
                    )

                    OutlineTextFieldWithErrorView(
                        value = email, onValueChange = updateEmail,
                        isError = emailError,
                        label = { Text("email") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Email
                        ),
                        errorMsg = "Enter A Valid Number",
                        colors = fieldColor(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "",
                                tint = Purple700
                            )
                        }
                    )

                    OutlineTextFieldWithErrorView(
                        value = phone, onValueChange = updatePhone,
                        isError = phoneError,
                        label = { Text("phone") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone
                        ),
//            visualTransformation = PrefixTransformation("(+880)"),
                        errorMsg = "Enter A Valid Number",
                        colors = fieldColor(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "",
                                tint = Purple700
                            )
                        }
                    )

                    var passwordVisibility: Boolean by remember { mutableStateOf(false) }

                    /**
                     * Password Field
                     */
                    OutlineTextFieldWithErrorView(
                        value = password, onValueChange = updatePassword,
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

                            val image =
                                painterResource(id = R.drawable.ic_baseline_remove_red_eye_24)

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

                    OutlineTextFieldWithErrorView(
                        value = referId,
                        onValueChange = updateReferId,
                        label = { Text("refer id") },
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = "",
                                tint = Purple700
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = goSearchReferScreen) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "",
                                    tint = Purple700
                                )
                            }
                        },
                        colors = fieldColor(),
                        isError = referIdError,
                        errorMsg = "Refer ID is empty"
                    )

                    Spacer(modifier = Modifier.height(4.dp))


                    val subscriptionTypes = listOf<String>(
                        "Select Subscription Type",
                        "Random Package",
                        "Classic Package"
                    )
                    var menuExpandedState by remember { mutableStateOf(false) }
                    var selectedIndex by remember { mutableStateOf(0) }

                    ComposeMenu(
                        menuItems = subscriptionTypes,
                        menuExpandedState = menuExpandedState,
                        seletedIndex = selectedIndex,
                        updateMenuExpandStatus = {
                            menuExpandedState = true
                        },
                        onDismissMenuView = {
                            menuExpandedState = false
                        },
                        onMenuItemclick = {
                            menuExpandedState = false
                            selectedIndex = it
                            updateSubscriptionType(it)
                        }
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    /** Submit Button */
                    Button(
                        enabled = submitButtonText == "Submit",
                        onClick = submitButtonClick,
                        shape = RoundedCornerShape(size = 22.5.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                    ) {
                        if (submitButtonText == "Loading...") {
                            CircularProgressIndicator(modifier = Modifier.size(30.dp))
                        }
                        Text(text = submitButtonText, fontSize = 16.sp)
                    }

                    errorText?.let {
                        Row {
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(it, fontSize = 14.sp, color = Color.Red)
                        }
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
                                Button(onClick = alertDialogDismiss, Modifier.fillMaxWidth()) {
                                    Text(text = "Dismiss", textAlign = TextAlign.Center)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SignupPrev() {
    MTransTheme {
        RegistrationScreen(
            name = "",
            updateName = {},
            nameError = false,
            username = "",
            updateUsername = {},
            usernameError = false,
            email = "",
            updateEmail = {},
            emailError = false,
            phone = "",
            updatePhone = {},
            phoneError = false,
            password = "",
            updatePassword = {},
            passwordError = false,
            referId = "",
            updateReferId = {},
            referIdError = false,
            goSearchReferScreen = { /*TODO*/ },
            submitButtonText = "Submit",
            submitButtonClick = { /*TODO*/ },
            errorText = null,
            alertData = null,
            alertDialogDismiss = { /*TODO*/ },
            updateSubscriptionType = {}
        )
    }
}
