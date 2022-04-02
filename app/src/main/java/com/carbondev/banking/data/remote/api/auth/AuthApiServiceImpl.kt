package com.carbondev.banking.data.remote.api.auth

import com.carbondev.banking.data.HttpRoutes
import com.carbondev.banking.data.dto.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AuthApiServiceImpl(
    private val client: HttpClient
) : AuthApiService {

    override suspend fun registration(registrationRequest: RegistrationRequest) =
        withContext(Dispatchers.IO) {
            client.post<AuthResponse> {
                url(HttpRoutes.REGISTRATION)
                contentType(ContentType.Application.Json)
                body = registrationRequest
            }
        }

    override suspend fun login(loginRequest: LoginRequest) =
        withContext(Dispatchers.IO) {
            client.post<AuthResponse> {
                url(HttpRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                body = loginRequest
            }
        }

    override suspend fun updateProfile(updateUserRequest: UpdateUserRequest): UpdateProfileResponse =
        withContext(Dispatchers.IO) {
            client.post<UpdateProfileResponse> {
                url(HttpRoutes.LOGIN)
                contentType(ContentType.Application.Json)
                body = updateUserRequest
            }
        }
}