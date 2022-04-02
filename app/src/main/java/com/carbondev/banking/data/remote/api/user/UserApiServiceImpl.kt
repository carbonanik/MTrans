package com.carbondev.banking.data.remote.api.user

import com.carbondev.banking.data.HttpRoutes
import com.carbondev.banking.data.dto.*
import com.carbondev.banking.data.model.User
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserApiServiceImpl(
    private val client: HttpClient
) : UserApiService {

    override suspend fun getAllUser(token: String): List<User> {
        return withContext(Dispatchers.IO) {
            client.get<List<User>> {
                url(HttpRoutes.GET_ALL_USER)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getUserByToken(token: String): User {
        return withContext(Dispatchers.IO) {
            client.get<User> {
                url(HttpRoutes.GET_USER_BY_TOKEN)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getUserById(userId: String, token: String): User {
        return withContext(Dispatchers.IO) {
            client.get<User> {
                url(HttpRoutes.GET_USER_BY_ID)
                parameter("id", userId)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun getUserByUserUsername(username: String, token: String): User {
        return withContext(Dispatchers.IO) {
            client.get<User> {
                url(HttpRoutes.GET_USER_BY_USERNAME)
                parameter("username", username)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun searchUserByName(query: String, token: String): List<User> {
        return withContext(Dispatchers.IO) {
            client.get<List<User>> {
                url(HttpRoutes.SEARCH_USER_BY_NAME)
                parameter("name", query)
                header(HttpHeaders.Authorization, token)
            }
        }
    }

    override suspend fun updateUser(updateRequest: UpdateUserRequest, token: String): User {
        return withContext(Dispatchers.IO) {
            client.post<User> {
                url(HttpRoutes.UPDATE_USER)
                contentType(ContentType.Application.Json)
                header(HttpHeaders.Authorization, token)
                body = updateRequest
            }
        }
    }

    override suspend fun deleteUserById(userId: String, token: String): String {
        return withContext(Dispatchers.IO) {
            client.delete<String> {
                url(HttpRoutes.DELETE_USER)
                parameter("id", userId)
            }
        }
    }
}