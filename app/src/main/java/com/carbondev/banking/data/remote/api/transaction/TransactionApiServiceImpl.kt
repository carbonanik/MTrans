package com.carbondev.banking.data.remote.api.transaction

import com.carbondev.banking.data.HttpRoutes
import com.carbondev.banking.data.dto.CreateTransactionRequest
import com.carbondev.banking.data.model.Transaction
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionApiServiceImpl(
    private val client: HttpClient
) : TransactionApiService {

    override suspend fun createTransaction(createTransactionRequest: CreateTransactionRequest, token: String): Transaction {
        return withContext(Dispatchers.IO) {
            client.post {
                url(HttpRoutes.CREATE_TRANSACTION)
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Authorization, token)
                body = createTransactionRequest
            }
        }
    }

    override suspend fun getAllTransaction(token: String): List<Transaction> {
        return withContext(Dispatchers.IO) {
            client.get {
                url(HttpRoutes.GET_ALL_TRANSACTION)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getTransactionById(transactionId: String, token: String): Transaction {
        return withContext(Dispatchers.IO) {
            client.get {
                url(HttpRoutes.GET_TRANSACTION_BY_ID)
                parameter("id", transactionId)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getTransactionForUser(userId: String, token: String): List<Transaction> {
        return withContext(Dispatchers.IO) {
            client.get {
                url(HttpRoutes.GET_TRANSACTION_FOR_USER)
                parameter("id", userId)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getTransactionBySender(
        senderId: String,
        token: String
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            client.get {
                url(HttpRoutes.GET_TRANSACTION_BY_SENDER)
                parameter("id", senderId)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getTransactionByReceiver(
        receiverId: String,
        token: String
    ): List<Transaction> {
        return withContext(Dispatchers.IO) {
            client.get {
                url(HttpRoutes.GET_TRANSACTION_BY_RECEIVER)
                parameter("id", receiverId)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

}