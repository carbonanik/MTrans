package com.carbondev.banking.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetUserRequest(
    val token: String
)
