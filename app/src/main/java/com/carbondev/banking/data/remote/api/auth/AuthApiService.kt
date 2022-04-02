package com.carbondev.banking.data.remote.api.auth

import com.carbondev.banking.data.dto.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 18-10-21
 */
interface AuthApiService {
    suspend fun registration(registrationRequest: RegistrationRequest): AuthResponse
    suspend fun login(loginRequest: LoginRequest): AuthResponse
    suspend fun updateProfile(updateUserRequest: UpdateUserRequest): UpdateProfileResponse
 }