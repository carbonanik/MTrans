package com.carbondev.banking.repository

import com.carbondev.banking.data.dto.UpdateUserRequest
import com.carbondev.banking.data.model.User
import com.carbondev.banking.data.remote.api.user.UserApiService
import com.carbondev.banking.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserApiRepository @Inject constructor(
    private val userApiService: UserApiService,
    private val userDataRepository: UserDataRepository
) {
    fun getAllUser(): Flow<Resource<List<User>>> =
        bound { userApiService.getAllUser(token()) }

    fun getUserByToken() =
        bound { userApiService.getUserByToken(token()) }

    fun getUserById(userId: String): Flow<Resource<User>> =
        bound { userApiService.getUserById(userId, token()) }

    fun getUserByUserUsername(username: String): Flow<Resource<User>> =
        bound { userApiService.getUserByUserUsername(username, token()) }

    fun searchUserByName(query: String): Flow<Resource<List<User>>> =
        bound { userApiService.searchUserByName(query, token()) }

    fun updateUser(updateRequest: UpdateUserRequest): Flow<Resource<User>> =
        bound { userApiService.updateUser(updateRequest, token()) }

    fun deleteUserById(userId: String): Flow<Resource<String>> =
        bound { userApiService.deleteUserById(userId, token()) }


    private suspend fun token() = userDataRepository.getToken()
}