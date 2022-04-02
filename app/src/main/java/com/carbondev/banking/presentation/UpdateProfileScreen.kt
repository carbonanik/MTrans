package com.carbondev.banking.presentation

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carbondev.banking.R
import com.carbondev.banking.ui.theme.MTransTheme
import com.carbondev.banking.ui.theme.Purple700

@Composable
fun UpdateProfileScreen(
    navController: NavController,
    viewModel: UpdateProfileViewModel,
    readActivityResultLauncher: ActivityResultLauncher<Intent>
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.collectProfileImage()
    })

    UpdateProfileScreen(
        profileImage = viewModel.profileImageBitmap,
        launchImageOpener = {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                addCategory(Intent.CATEGORY_OPENABLE)
                type = "image/png"
            }
            readActivityResultLauncher.launch(intent)
        },
        name = viewModel.name,
        updateName = { data: String ->
            viewModel.name = data

        },

        email = viewModel.email,
        updateEmail = { data: String ->
            viewModel.email = data
        }  ,

        phone = viewModel.phone,
        updatePhone = { data: String ->
            viewModel.phone = data
        },
        nid = viewModel.nid,
        updateNid = { data: String ->
            viewModel.nid = data
        },

        password = viewModel.password,
        updatePassword = { data: String ->
            viewModel.password = data
        },
        submitButtonText = viewModel.submitButtonText,
        submitClick = {
            viewModel.updateProfile()
        },
        errorText = viewModel.errorText,
        alertData = viewModel.alertData,
        updateAlertData = {
            viewModel.alertData = null
        }
    )

}

@Composable
fun UpdateProfileScreen(
    profileImage: ImageBitmap?,
    launchImageOpener: () -> Unit,

    name: String,
    updateName: (data: String) -> Unit,

    email: String,
    updateEmail: (data: String) -> Unit,

    phone: String,
    updatePhone: (data: String) -> Unit,

    nid: String,
    updateNid: (data: String) -> Unit,

    password: String,
    updatePassword: (data: String) -> Unit,

    submitButtonText: String,
    submitClick: () -> Unit,
    errorText: String?,
    alertData: AlertDialogData?,
    updateAlertData: () -> Unit
    ) {



    Surface(modifier = Modifier.fillMaxSize()) {
        Column {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.mtrans_logo),
                    contentDescription = "",
                    Modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))

                if (profileImage != null) {
                    Image(
                        bitmap = profileImage,
                        contentDescription = "",
                        Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .clickable {
                                launchImageOpener()
                            }
                    )
                } else
                {
                    Image(
                        painter = painterResource(id = R.drawable.female_profile),
                        contentDescription = "",
                        Modifier
                            .size(150.dp)
                            .clip(CircleShape)
                            .clickable {
                                launchImageOpener()
                            }
                    )
                }


                /**
                 *  Name
                 */
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        updateName(it)
                                    },
                    label = { Text("name") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "",
                            tint = Purple700
                        )
                    },
                    colors = fieldColor(),

                    )

                Spacer(modifier = Modifier.height(16.dp))

                /**
                 * Email
                 */
                OutlinedTextField(
                    value = email,
                    onValueChange = updateEmail,
                    label = { Text("email") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "",
                            tint = Purple700
                        )
                    },
                    colors = fieldColor()
                )

                Spacer(modifier = Modifier.height(16.dp))

                /**
                 * Phone
                 */
                OutlinedTextField(
                    value = phone,
                    onValueChange = updatePhone,
                    label = { Text("phone") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "",
                            tint = Purple700
                        )
                    },
                    colors = fieldColor()

                )

                Spacer(modifier = Modifier.height(16.dp))


//            /**
//             * Username
//             */
//            OutlinedTextField(
//                value = viewModel.username,
//                onValueChange = { data: String ->
//                    viewModel.username = data
//                },
//                label = { Text("username") },
//                modifier = Modifier.fillMaxWidth(),
//                leadingIcon = {
//                    Icon(imageVector = Icons.Default.Person, contentDescription = "")
//                }
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))

                /**
                 * NID
                 */
                OutlinedTextField(
                    value = nid,
                    onValueChange = updateNid,
                    label = { Text("NID") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "",
                            tint = Purple700
                        )
                    },
                    colors = fieldColor()
                )

                Spacer(modifier = Modifier.height(16.dp))

                /**
                 * Password
                 */
                OutlinedTextField(
                    value = password,
                    onValueChange = updatePassword,
                    label = { Text("password") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "",
                            tint = Purple700
                        )
                    },
                    colors = fieldColor()
                )

                Spacer(modifier = Modifier.height(50.dp))
                /**
                 * Submit Button
                 */
                Button(
                    enabled = submitButtonText == UPDATE,
                    onClick = submitClick,
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
                            Button(onClick = updateAlertData, Modifier.fillMaxWidth()) {
                                Text(text = "Dismiss", textAlign = TextAlign.Center)
                            }
                        },
                    )
                }

            }
        }
    }
}

@Preview
@Composable
fun UpdatePrev() {
    MTransTheme {
        UpdateProfileScreen(
            profileImage = null,
            launchImageOpener = { /*TODO*/ },
            name = "Anik",
            updateName = {},
            email = "",
            updateEmail = {},
            phone = "",
            updatePhone = {},
            nid = "",
            updateNid = {},
            password = "",
            updatePassword = {},
            submitButtonText = "Update",
            submitClick = { /*TODO*/ },
            errorText = null,
            alertData = null
        ) {
        }
    }
}