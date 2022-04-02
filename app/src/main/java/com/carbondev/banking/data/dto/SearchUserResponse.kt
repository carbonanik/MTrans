package com.carbondev.banking.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class SearchUserResponse(
    val msg: String,
    val status: Boolean,
    val users: List<MiniUser>? = null
)