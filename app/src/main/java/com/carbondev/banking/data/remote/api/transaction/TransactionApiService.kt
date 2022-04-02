package com.carbondev.banking.data.remote.api.transaction

import android.view.SurfaceControl
import com.carbondev.banking.data.dto.CreateTransactionRequest
import com.carbondev.banking.data.model.Transaction

interface TransactionApiService {
    suspend fun createTransaction(createTransactionRequest: CreateTransactionRequest, token: String): Transaction
    suspend fun getAllTransaction(token: String): List<Transaction>
    suspend fun getTransactionById(transactionId: String, token: String): Transaction
    suspend fun getTransactionForUser(userId: String, token: String): List<Transaction>
    suspend fun getTransactionBySender(senderId: String, token: String): List<Transaction>
    suspend fun getTransactionByReceiver(receiverId: String, token: String): List<Transaction>
}