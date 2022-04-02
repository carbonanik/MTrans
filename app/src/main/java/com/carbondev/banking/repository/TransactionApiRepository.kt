package com.carbondev.banking.repository

import com.carbondev.banking.data.dto.CreateTransactionRequest
import com.carbondev.banking.data.model.Transaction
import com.carbondev.banking.data.remote.api.transaction.TransactionApiService
import com.carbondev.banking.util.Resource
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionApiRepository @Inject constructor(
    private val transactionApiService: TransactionApiService,
    val userDataRepository: UserDataRepository
) {

    fun createTransaction(
        createTransactionRequest: CreateTransactionRequest
    ): Flow<Resource<Transaction>> = bound {
        transactionApiService.createTransaction(createTransactionRequest, token())
    }

    fun getAllTransaction(): Flow<Resource<List<Transaction>>> =
        bound { transactionApiService.getAllTransaction(token()) }

    fun getTransactionById(transactionId: String): Flow<Resource<Transaction>> =
        bound { transactionApiService.getTransactionById(transactionId, token()) }

    fun getTransactionForUser(userId: String): Flow<Resource<List<Transaction>>> =
        bound { transactionApiService.getTransactionForUser(userId, token()) }

    fun getTransactionBySender(senderId: String): Flow<Resource<List<Transaction>>> =
        bound { transactionApiService.getTransactionBySender(senderId, token()) }

    fun getTransactionByReceiver(receiverId: String): Flow<Resource<List<Transaction>>> =
        bound { transactionApiService.getTransactionByReceiver(receiverId, token()) }


    private suspend fun token(): String {
        return userDataRepository.getToken()
    }
}