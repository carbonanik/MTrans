package com.carbondev.banking.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateUserRequest(
    val id: String,
    val name: String? = null,
    @SerialName("phone_no")
    val phoneNo: String? = null,
    val email: String? = null,
    val nid: String? = null,
    val photo: String? = null,
    val password: String? = null
)