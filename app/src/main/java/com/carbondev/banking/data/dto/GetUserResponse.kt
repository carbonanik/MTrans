package com.carbondev.banking.data.dto

import com.carbondev.banking.data.model.User
import kotlinx.serialization.Serializable

@Serializable
data class GetUserResponse(
    val balance: Int,
    val msg: String,
    val status: Boolean,
    val user: User
)

