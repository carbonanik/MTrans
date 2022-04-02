package com.carbondev.banking.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.carbondev.banking.R
import com.carbondev.banking.data.model.User
import com.carbondev.banking.ui.theme.Purple500


@Preview
@Composable
fun DashboardPreview() {
    DashboardScreen(
        user = User("anik", "anik", "Anik", "0176678"),
       {}, null, 100, {}, {}, {}
    )
}

@Composable
fun DashboardScreen(
    navController: NavController,
    viewModel: DashboardViewModel
) {
    val user by viewModel.user.collectAsState(initial = null)

    DashboardScreen(
        user,
        { viewModel.collectProfileImage() },
        viewModel.profileImageBitmap.asImageBitmap(),
        viewModel.userResponse?.data?.balance,
        { navController.navigate(Screen.UpdateProfile.route) },
        {navController.navigate(Screen.RegistrationScreen.route)},
        {viewModel.logOut()}
    )
    if (viewModel.loggedOut) {
        navController.popBackStack()
        navController.navigate(Screen.LoginScreen.route)
        viewModel.loggedOut = false
    }

}

@Composable
fun DashboardScreen(
    user: User?,
    collectProfileImage: () -> Unit,
    imageBitmap: ImageBitmap?,
    balance: Int? ,
    goUpdateProfile: () -> Unit,
    goResistration: () -> Unit,
    logOut: () -> Unit
) {

    LaunchedEffect(key1 = true, block = {
        collectProfileImage()
    })

    Surface {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.height(5.dp))
                Image(
                    painter = painterResource(id = R.drawable.mtrans_logo),
                    contentDescription = "",
                    Modifier.width(140.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Divider()
                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    Modifier
                        .padding(8.dp)
                        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(10.dp))
                    if (imageBitmap != null){
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = "",
                            Modifier
                                .size(180.dp)
                                .clip(CircleShape)
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.female_profile),
                            contentDescription = "",
                            Modifier
                                .size(180.dp)
                                .clip(CircleShape)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    Column(Modifier.padding(horizontal = 20.dp, vertical = 30.dp)) {
                        Text(
                            text = "Name: ${user?.name ?: "Unknown"}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            color = Purple500,
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.padding(top = 10.dp)
                        ) {
                            Text(
                                text = "Balance: ${balance ?: 0}",
                                Modifier.padding(8.dp),
                                color = Color.White
                            )
                        }
                    }
                }
            }
            Divider()

            // cross box
            Box() {
                Image(
                    painter = painterResource(id = R.drawable.g3326),
                    contentDescription = "",
                    Modifier.fillMaxWidth()
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OptionItem(
                            name = "PROFILE UPDATE",
                            resId = R.drawable.registration,
                            Modifier.padding(top = 10.dp),
                            color = Color.Red,
                            onClick = {
                                goUpdateProfile()
                            }
                        )
                    }
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OptionItem(
                            name = "REGISTRATION",
                            resId = R.drawable.add_user_male,
                            Modifier.padding(end = 10.dp),
                            onClick = {
                                goResistration()
                            }
                        )
                        OptionItem(
                            name = "WITHDRAW",
                            resId = R.drawable.insert_card,
                            Modifier.padding(start = 10.dp),
                            onClick = {}
                        )
                    }
                    Row(
                        Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OptionItem(
                            name = "HISTORY",
                            resId = R.drawable.time_machine,
                            Modifier.padding(bottom = 10.dp),
                            onClick = {}
                        )
                    }
                }
            }
        }
    }
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        IconButton(
            onClick = {
                logOut()
            },
            Modifier
                .padding(top = 14.dp, end = 16.dp)
                .background(shape = CircleShape, color = Purple500)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_login_24),
                contentDescription = "",
                tint = Color.White
            )
        }
    }

}

@Composable
fun OptionItem(
    name: String, resId: Int,
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = rememberRipple(false),
            onClick = onClick
        )
    ) {
        Icon(
            painter = painterResource(id = resId),
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = color
        )
        Text(text = name, fontSize = 18.sp, color = color)
    }
}