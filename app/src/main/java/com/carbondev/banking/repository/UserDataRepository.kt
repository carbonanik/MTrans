package com.carbondev.banking.repository

import com.carbondev.banking.data.local.pref.DataStoreManager
import com.carbondev.banking.data.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend fun storeUser(user: User) {
        dataStoreManager.storeUser(user)
    }

    suspend fun storeToken(token: String) {
        dataStoreManager.storeToken(token)
    }

    suspend fun getUser(): User? {
        return dataStoreManager.savedUser.first()
    }

    fun getUserFlow(): Flow<User?> {
        return dataStoreManager.savedUser
    }

    suspend fun getToken(): String {
        return dataStoreManager.savedToken.first()
    }

    suspend fun storeBalance(balance: Int) {
        dataStoreManager.storeBalance(balance)
    }

    suspend fun getBalance(): Int? {
        return dataStoreManager.savedBalance.first()
    }

    suspend fun clear() {
        dataStoreManager.clear()
    }
}