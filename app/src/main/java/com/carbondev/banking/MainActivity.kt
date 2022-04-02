package com.carbondev.banking

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.carbondev.banking.presentation.*
import com.carbondev.banking.ui.theme.MTransTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var readActivityResultLauncher: ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel by viewModels<MainViewModel>()
        val registrationViewModel by viewModels<RegistrationViewModel>()
        val updateProfileViewModel by viewModels<UpdateProfileViewModel>()

        readActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

                if (result.resultCode == Activity.RESULT_OK) {
                    result?.data?.data?.also { uri ->
                        Log.i("Uri Write", "Uri: $uri")
                        val imageStream = contentResolver.openInputStream(uri)

                        lifecycleScope.launch {
                            updateProfileViewModel.updateProfile(
                                updateProfileViewModel.encodeImage(imageStream)
                            )
                        }

                        Toast.makeText(this, "Picture Selected", Toast.LENGTH_LONG).show()
                    }
                }
            }


        setContent {
            MTransTheme {
                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {

                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.navigationBarsPadding()
                    ) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.StartingScreen.route
                        ) {
                            composable(Screen.StartingScreen.route) {
                                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                                    if (viewModel.isLoggedIn == true) {
                                        println("logged in")
                                        navController.popBackStack()
                                        navController.navigate(Screen.DashboardScreen.route)
                                        viewModel.isLoggedIn = null
                                    } else if (viewModel.isLoggedIn == false) {
                                        println("not logged in")
                                        navController.popBackStack()
                                        navController.navigate(Screen.LoginScreen.route)
                                        viewModel.isLoggedIn = null
                                    }
                                    Image(
                                        painter = painterResource(id = R.drawable.bingeid_logo_2),
                                        contentDescription = "",
                                        Modifier.size(330.dp)
                                    )
                                }
                            }
                            composable(Screen.LoginScreen.route) {
                                LoginScreen(
                                    navController = navController,
                                    viewModel = hiltViewModel()
                                ) {}
                            }
                            composable(Screen.RegistrationScreen.route) {
                                RegistrationScreen2(
                                    navController = navController,
                                    viewModel = registrationViewModel
                                )
                            }
                            composable(Screen.DashboardScreen.route) {
                                DashboardScreen(
                                    navController = navController,
                                    viewModel = hiltViewModel()
                                )
                            }
                            composable(Screen.UpdateProfile.route) {
                                UpdateProfileScreen(
                                    navController = navController,
                                    viewModel = hiltViewModel(),
                                    readActivityResultLauncher = readActivityResultLauncher
                                )
                            }
                            composable(Screen.SearchSponsorScreen.route) {
                                SearchSponsorScreen(
                                    navController = navController,
                                    viewModel = registrationViewModel
                                )
                            }
                            composable(Screen.SearchReferScreen.route) {
                                SearchReferScreen(
                                    navController = navController,
                                    viewModel = registrationViewModel
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun actionOpenDoc() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"
        }

        readActivityResultLauncher.launch(intent)
    }
}