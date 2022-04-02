package com.carbondev.banking.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MiniUser(
    val id: Int,
    val username: String
)