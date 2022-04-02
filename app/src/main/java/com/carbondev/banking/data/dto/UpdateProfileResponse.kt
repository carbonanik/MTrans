package com.carbondev.banking.data.dto

import com.carbondev.banking.data.model.User
import kotlinx.serialization.Serializable

@Serializable
class UpdateProfileResponse (
    val status: Boolean,
    val msg: String,
    val user: User? = null
)

