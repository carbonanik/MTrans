package com.carbondev.banking.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbondev.banking.data.dto.AuthResponse
import com.carbondev.banking.data.dto.RegistrationRequest
import com.carbondev.banking.data.dto.SearchUserResponse
import com.carbondev.banking.data.model.User
import com.carbondev.banking.domail.use_case.RegistrationUseCase
import com.carbondev.banking.repository.UserApiRepository
import com.carbondev.banking.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registrationUseCase: RegistrationUseCase,
    private val userApiRepository: UserApiRepository
) : ViewModel() {

    var response by mutableStateOf<Resource<AuthResponse>?>(null)
    var userList by mutableStateOf<List<User>>(emptyList())

    var name by mutableStateOf("")
    var username by mutableStateOf("")
    var email by mutableStateOf("")
    var phone by mutableStateOf("")
    var nid by mutableStateOf("")
    var password by mutableStateOf("")

    var sponsorId by mutableStateOf("")
    var sponsorIdPrediction by mutableStateOf(mutableListOf(""))

    var referId by mutableStateOf("")
    var referIdPrediction by mutableStateOf(mutableListOf(""))

    var subscriptionType by mutableStateOf(0)


    var nameError by mutableStateOf(false)
    var usernameError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var phoneError by mutableStateOf(false)
    var nidError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)
    var sponsorIdError by mutableStateOf(false)
    var referIdError by mutableStateOf(false)


    var submitButtonText by mutableStateOf("Submit")
    var errorText: String? by mutableStateOf(null)

    var alertData by mutableStateOf<AlertDialogData?>(null)


    init {
        viewModelScope.launch {
            userApiRepository.getAllUser().collect {

                userList = it.data ?: emptyList()
            }
        }
    }

    private fun registration() {
        viewModelScope.launch {
            val referIDNO = userList.firstOrNull {
                it.username == referId
            }?.id ?: 0

            val sponsorIDNO = userList.firstOrNull {
                it.username == sponsorId
            }?.id ?: 0

            val registrationRequest = RegistrationRequest(
                name, username, phone, password, email, nid,
                photo = "", role = 0, referIDNO.toString(),
                sponsorIDNO.toString(), status = 0,
                subscriptionType.toString()
            )

            registrationUseCase(registrationRequest)
                .collect {
                    response = it
                    doWithResponse()
                }
        }
    }

    fun sponsorQuery(q: String) {
        sponsorIdPrediction = userList
            .filter { it.username.contains(q) }
            .map { it.username }
            .toMutableList()
    }

    fun referQuery(q: String) {
        referIdPrediction = userList
            .filter { it.username.contains(q) }
            .map { it.username }
            .toMutableList()
    }

    fun formValidate() {
        nameError = !name.nameIsValid()
        usernameError = !username.usernameIsValid()
        phoneError = !phone.phoneIsValid()
        passwordError = !password.passwordIsValid()
        emailError = !email.emailIsValid()
        sponsorIdError = !sponsorId.sponsorIdIsValid()
        referIdError = !referId.referIdIsValid()
        nidError = !nid.nidIsValid()

        if (!(nameError || usernameError || emailError
                    || phoneError || passwordError
                    || sponsorIdError || referIdError || nidError
                    )
        ) {
            registration()
        }
    }

    private fun doWithResponse() {
        when (response) {
            is Resource.Loading -> {
                submitButtonText = LOADING
                errorText = ""
            }
            is Resource.Success -> {
                submitButtonText = SUBMIT
                errorText = ""
                alertData = AlertDialogData("Success", "User Successfully Created")
                clearFields()
                //            response = null
            }
            is Resource.Error -> {
                submitButtonText = SUBMIT
                errorText = "Error: ${response?.errorMessage}"
                alertData = AlertDialogData("Error", response?.errorMessage ?: "Error Occurred")
            }
        }
    }

    fun clearFields() {
        name = ""
        username = ""
        email = ""
        phone = ""
        nid = ""
        password = ""
        sponsorId = ""
        referId = ""
        subscriptionType = 0
    }

}