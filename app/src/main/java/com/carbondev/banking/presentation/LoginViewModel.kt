package com.carbondev.banking.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbondev.banking.data.dto.LoginRequest
import com.carbondev.banking.data.dto.LoginResponse
import com.carbondev.banking.domail.use_case.LoginUseCase
import com.carbondev.banking.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    var loginResponse by mutableStateOf<Resource<LoginResponse>?>(null)

    var alertData by mutableStateOf<AlertDialogData?>(null)

    fun login(username: String, password: String) {
        val loginRequest = LoginRequest(username, password)
        viewModelScope.launch {
            /*loginUseCase(loginRequest).collect {
                response = it
                doWithResponse()
            }*/
        }
    }

    private fun doWithResponse() {
        if (loginResponse is Resource.Success) {
            alertData = AlertDialogData("Success", "Login Successful")
        }
        else if (loginResponse is Resource.Error) {
            alertData = AlertDialogData("Error", loginResponse?.errorMessage ?: "Error Occurred")
        }
    }


}