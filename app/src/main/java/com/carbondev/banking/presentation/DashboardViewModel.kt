package com.carbondev.banking.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbondev.banking.R
import com.carbondev.banking.data.ProfileImage
import com.carbondev.banking.data.dto.GetUserResponse
import com.carbondev.banking.domail.use_case.GetMeUseCase
import com.carbondev.banking.repository.UserDataRepository
import com.carbondev.banking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val getMeUseCase: GetMeUseCase,
    private val profileImage: ProfileImage,
    @ApplicationContext context: Context
) : ViewModel() {

    var profileImageBitmap by mutableStateOf<Bitmap>(
        BitmapFactory.decodeResource(
            context.resources,
            R.drawable.female_profile
        )
    )

    var loggedOut by mutableStateOf(false)
    fun logOut() {
        viewModelScope.launch {
            userDataRepository.clear()
            loggedOut = true
        }
    }

    var user = userDataRepository.getUserFlow()
    var userResponse by mutableStateOf<Resource<GetUserResponse>?>(null)

    init {
        println("init dashboard viewmodel")
        viewModelScope.launch {
//            getMeUseCase().collect {
//                userResponse = it
//            }
        }
//        profileImage.getProfilePhoto()?.let {
//            profileImageBitmap = it
//        }
//        collectProfileImage()
    }

    fun collectProfileImage() {
        viewModelScope.launch {
            profileImage.downloadBitmap()?.let {
                println("Dashboard:bitmap downloaded")
                profileImageBitmap = it
            }
        }
    }
}