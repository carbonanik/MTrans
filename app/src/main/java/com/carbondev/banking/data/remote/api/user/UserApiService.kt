package com.carbondev.banking.data.remote.api.user

import com.carbondev.banking.data.dto.*
import com.carbondev.banking.data.model.User

interface UserApiService {
    suspend fun getAllUser(token: String): List<User>
    suspend fun getUserByToken(token: String): User
    suspend fun getUserById(userId: String, token: String): User
    suspend fun getUserByUserUsername(username: String, token: String): User
    suspend fun searchUserByName(query: String, token: String): List<User>
    suspend fun updateUser(updateRequest: UpdateUserRequest, token: String): User
    suspend fun deleteUserById(userId: String, token: String): String
}