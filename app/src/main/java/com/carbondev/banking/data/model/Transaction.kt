package com.carbondev.banking.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.carbondev.banking.data.model.TransactionType.*
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class Transaction(
    val id: String,
    val amount: Float,
    val transactionTime: Long,
    val senderId: String,
    val receiverId: String,
    val transactionType: TransactionType
)


enum class TransactionType(val value: String) {
    CASH_IN("Cash Out"),
    CASH_OUT("Cash In"),
    PAYMENT("Payment"),
    PAYMENT_RECEIVE("Payment Receive"),
    SEND_MONEY("Send Money"),
    RECEIVE_MONEY("Receive Money")
}

fun TransactionType.getIcon(): ImageVector {
    return when (this) {
        CASH_IN, PAYMENT_RECEIVE, RECEIVE_MONEY -> Icons.Default.ArrowForward
        CASH_OUT, PAYMENT, SEND_MONEY -> Icons.Default.ArrowBack
    }
}

fun TransactionType.getColor(): Color {
    return when (this) {
        CASH_IN, PAYMENT_RECEIVE, RECEIVE_MONEY -> Color(0xFF19D319)
        CASH_OUT, PAYMENT, SEND_MONEY -> Color.Red
    }
}

fun Transaction.timeAndDate(): String =
    SimpleDateFormat("MMMM dd, yyyy h:mm a", Locale.getDefault()).format(transactionTime)
        ?: "Invalid Time"

