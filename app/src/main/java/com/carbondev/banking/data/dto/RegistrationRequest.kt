package com.carbondev.banking.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RegistrationRequest(
    val name: String,
    val username: String,
    @SerialName("phone_no")
    val phoneNo: String,
    val password: String,
    val email: String? = null,
    val nid: String? = null,
    val photo: String? = null,
    val role: Int? = null,
    @SerialName("refer_id")
    val referId: String? = null,
    @SerialName("sponsor_id")
    val sponsorId: String? = null,
    val status: Int? = null,
    @SerialName("subscribe_type")
    val subscriptionType: String? = null
)
