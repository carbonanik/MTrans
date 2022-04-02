package com.carbondev.banking.presentation

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbondev.banking.R
import com.carbondev.banking.data.ProfileImage
import com.carbondev.banking.data.dto.UpdateProfileResponse
import com.carbondev.banking.repository.UserDataRepository
import com.carbondev.banking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import javax.inject.Inject


@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val profileImage: ProfileImage,
    @ApplicationContext context: Context
) : ViewModel() {

    var response
            by mutableStateOf<Resource<UpdateProfileResponse>?>(null)

    var profileImageBitmap by mutableStateOf<ImageBitmap>(
        BitmapFactory.decodeResource(
            context.resources,
            R.drawable.female_profile
        ).asImageBitmap()
    )

    var name by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var nid by mutableStateOf("")
    var password by mutableStateOf("")

    //    var photoBase64: String? = null
    var photoUri = ""
    var photoUrl by mutableStateOf("")

    var errorText: String? by mutableStateOf(null)
    var submitButtonText by mutableStateOf(SUBMIT)

    var alertData by mutableStateOf<AlertDialogData?>(null)

    init {
        viewModelScope.launch {
//            val user = userDataRepository.getUser()
//            if (user != null) {
//                name = user.name
//                if (user.email != null) {
//                    email = user.email
//                }
//                phone = user.phoneNo
//                photoUrl = user.photo
//                nid = user.nid
//            }
        }
        profileImage.getProfilePhoto()?.let {
            profileImageBitmap = it.asImageBitmap()
        }
    }

    fun collectProfileImage() {
        viewModelScope.launch {
            delay(1000)
            profileImage.downloadBitmap()?.let {
                println("UpdateProfile:bitmap downloaded")
                profileImageBitmap = it.asImageBitmap()
            }
        }
    }

    fun updateProfile(photoBase64: String? = null) {
        println("update profile")
        viewModelScope.launch {
//            updateProfileUseCase(
//                name = name,
//                email = email,
//                phone_no = phone,
//                photo = photoBase64,
//                nid = nid,
//                password = password
//            ).collect {
//                response = it
//                doWithResponse()
//            }
        }
    }

    private fun doWithResponse() {
        println("do with response")
        println(response)
        when (response) {
            is Resource.Loading -> {
                submitButtonText = LOADING
                errorText = ""
            }
            is Resource.Success -> {
                println("profile update success")
                submitButtonText = SUBMIT
                errorText = ""
                alertData = AlertDialogData("Success", "Profile Successfully Updated")
                collectProfileImage()
            }
            is Resource.Error -> {
                submitButtonText = SUBMIT
                errorText = "Error: ${response?.errorMessage}"
                alertData = AlertDialogData("Error", response?.errorMessage ?: "Error Occurred")
            }
        }
    }

    suspend fun encodeImage(imageStream: InputStream?) = withContext(Dispatchers.Default) {
        val bm = BitmapFactory.decodeStream(imageStream)
        val byteArray = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.PNG, 70, byteArray)
//        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b: ByteArray = byteArray.toByteArray()
        val str64 = Base64.encodeToString(b, Base64.DEFAULT)
        return@withContext "data:image/png;base64,$str64"
    }
}
