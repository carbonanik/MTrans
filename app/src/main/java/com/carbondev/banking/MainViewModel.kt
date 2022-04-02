package com.carbondev.banking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carbondev.banking.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
): ViewModel() {

    var isLoggedIn by mutableStateOf<Boolean?>(null)

    init {
        isLoggedIn()
    }

    fun isLoggedIn(){
        viewModelScope.launch {
            isLoggedIn = userDataRepository.getUser() != null
        }
    }
}