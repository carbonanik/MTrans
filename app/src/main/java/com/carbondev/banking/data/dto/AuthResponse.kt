package com.carbondev.banking.data.dto

import com.carbondev.banking.data.model.User
import kotlinx.serialization.Serializable

@Serializable
class AuthResponse (
    val user: User,
    val token: String
)