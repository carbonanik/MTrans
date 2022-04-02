package com.carbondev.banking.presentation

sealed class Screen(val route: String){
    object LoginScreen: Screen("login_screen")
    object RegistrationScreen: Screen("registration_screen")
    object DashboardScreen: Screen("dashboard_screen")
    object UpdateProfile: Screen("update_profile")
    object StartingScreen: Screen("starting_screen")
    object SearchSponsorScreen: Screen("search_sponsor_screen")
    object SearchReferScreen: Screen("search_refer_screen")

}
