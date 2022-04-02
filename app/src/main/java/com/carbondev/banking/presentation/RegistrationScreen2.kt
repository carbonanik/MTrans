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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carbondev.banking.R
import com.carbondev.banking.ui.custom_view.ComposeMenu
import com.carbondev.banking.ui.theme.Purple700
import com.carbondev.banking.util.*


@Composable
fun RegistrationScreen2(
    navController: NavController,
    viewModel: RegistrationViewModel,
) {

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(start = 16.dp, end = 16.dp, top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Image(
                painter = painterResource(id = R.drawable.bingeid_logo_2),
                contentDescription = "",
                Modifier.width(100.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Create a new account")


            OutlineTextFieldWithErrorView(
                value = viewModel.name, onValueChange = { data: String ->
                    if (data.length <= 100) viewModel.name = data
                    if (viewModel.name.nameIsValid()) viewModel.nameError = false
                },
                isError = viewModel.nameError,
                label = { Text("name") },
                modifier = Modifier.fillMaxWidth(),
                errorMsg = "Name is empty",
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
                value = viewModel.username, onValueChange = { data: String ->
                    viewModel.username = data
                    if (viewModel.username.usernameIsValid()) viewModel.usernameError = false
                },
                isError = viewModel.usernameError,
                label = { Text("username") },
                modifier = Modifier.fillMaxWidth(),
                errorMsg = "Enter a valid username ",
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
                value = viewModel.email, onValueChange = { data: String ->
                    viewModel.email = data
                    if (viewModel.email.emailIsValid()) viewModel.emailError = false
                },
                isError = viewModel.emailError,
                label = { Text("email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                errorMsg = "Enter a valid email",
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
                value = viewModel.phone, onValueChange = { data: String ->
                    if (data.length <= 20) viewModel.phone = data
                    if (viewModel.phone.phoneIsValid()) viewModel.phoneError = false
                },
                isError = viewModel.phoneError,
                label = { Text("phone") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Phone
                ),
//            visualTransformation = PrefixTransformation("(+880)"),
                errorMsg = "Enter a valid number",
                colors = fieldColor(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "",
                        tint = Purple700
                    )
                }
            )

            OutlineTextFieldWithErrorView(
                value = viewModel.nid, onValueChange = { data: String ->
                    viewModel.nid = data
                    if (viewModel.nid.nidIsValid()) viewModel.nidError = false
                },
                isError = viewModel.nidError,
                label = { Text("nid") },
                modifier = Modifier.fillMaxWidth(),
                errorMsg = "NID is empty",
                colors = fieldColor(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.AccountBox,
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
                value = viewModel.password, onValueChange = { data: String ->
                    viewModel.password = data
                    if (viewModel.password.passwordIsValid()) viewModel.passwordError = false
                },
                isError = viewModel.passwordError,
                label = { Text("password") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                errorMsg = "Password too short",
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None
                else PasswordVisualTransformation(),
                trailingIcon = {

                    val image = painterResource(id = R.drawable.ic_baseline_remove_red_eye_24)

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
                value = viewModel.referId,
                onValueChange = { data: String ->
                    viewModel.referId = data
                    if (viewModel.referId.referIdIsValid()) viewModel.referIdError = false
                },
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
                    IconButton(onClick = {
                        navController.navigate(Screen.SearchReferScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Purple700
                        )
                    }
                },
                colors = fieldColor(),
                isError = viewModel.referIdError,
                errorMsg = "Refer ID is empty"
            )

            OutlineTextFieldWithErrorView(
                value = viewModel.sponsorId,
                onValueChange = { data: String ->
                    viewModel.sponsorId = data
                    if (viewModel.sponsorId.sponsorIdIsValid()) viewModel.sponsorIdError = false

                },
                label = { Text("sponsor id") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "",
                        tint = Purple700
                    )

                },
                trailingIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.SearchSponsorScreen.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "",
                            tint = Purple700
                        )
                    }
                },
                colors = fieldColor(),
                isError = viewModel.sponsorIdError,
                errorMsg = "Sponsor ID is Empty"
            )
            Spacer(modifier = Modifier.height(4.dp))

            val subscriptionTypes =
                listOf<String>("Select Subscription Type", "Random Package", "Classic Package")
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
                    viewModel.subscriptionType = it
                }
            )

            Spacer(modifier = Modifier.height(22.dp))

            /** Submit Button */
            Button(
                enabled = viewModel.submitButtonText == "Submit",
                onClick = {
                    viewModel.formValidate()
                },
                shape = RoundedCornerShape(size = 22.5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
            ) {
                if (viewModel.submitButtonText == "Loading...") {
                    CircularProgressIndicator(modifier = Modifier.size(30.dp))
                }
                Text(text = viewModel.submitButtonText, fontSize = 16.sp)
            }

            viewModel.errorText?.let {
                Row {
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(it, fontSize = 14.sp, color = Color.Red)
                }
            }

            Spacer(modifier = Modifier.height(450.dp))

            viewModel.alertData?.let { data ->
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
                            viewModel.alertData = null
                        }, Modifier.fillMaxWidth()) {
                            Text(text = "Dismiss", textAlign = TextAlign.Center)
                        }
                    },
                )
            }

        }
    }
}
