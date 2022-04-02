package com.carbondev.banking.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CreateTransactionRequest(
//    val transactionID: String,
    val amount: Float,
    val senderId: String,
    val receiverId: String,
    val transactionType: Int
)
