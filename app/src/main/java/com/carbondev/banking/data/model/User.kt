package com.carbondev.banking.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val username: String,
    val name: String,
    @SerialName("phone_no")
    val phoneNo: String,
    val email: String? = null,
    val nid: String? = null,
    val photo: String? = null,
    val role: Int? = null,
    @SerialName("created_at")
    val createdAt: Long = System.currentTimeMillis(),
    @SerialName("refer_id")
    val referId: String? = null,
    @SerialName("sponsor_id")
    val sponsorId: String? = null,
    val status: Int? = null,
    @SerialName("subscribe_type")
    val subscriptionType: String? = null,
    @SerialName("updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
    val balance: Float = 0.0f
)