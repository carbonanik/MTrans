package com.carbondev.banking.data.dto

import com.carbondev.banking.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val balance: Int,
    val msg: String,
    val status: Boolean,
    val token: String,
    val user: User
)

