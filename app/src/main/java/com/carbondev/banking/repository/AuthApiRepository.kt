package com.carbondev.banking.repository

import com.carbondev.banking.data.remote.api.auth.AuthApiService
import com.carbondev.banking.data.dto.*
import com.carbondev.banking.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthApiRepository @Inject constructor(
    private val authApiService: AuthApiService
) {
    fun login(loginRequest: LoginRequest): Flow<Resource<AuthResponse>> {
        return bound { authApiService.login(loginRequest) }
    }

    fun registration(registrationRequest: RegistrationRequest): Flow<Resource<AuthResponse>> {
        return bound { authApiService.registration(registrationRequest) }
    }
}