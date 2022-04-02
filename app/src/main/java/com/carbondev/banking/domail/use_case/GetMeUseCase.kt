package com.carbondev.banking.domail.use_case

import com.carbondev.banking.data.dto.GetUserRequest
import com.carbondev.banking.data.dto.GetUserResponse
import com.carbondev.banking.data.model.User
import com.carbondev.banking.repository.AuthApiRepository
import com.carbondev.banking.repository.UserApiRepository
import com.carbondev.banking.repository.UserDataRepository
import com.carbondev.banking.util.Resource
import com.carbondev.banking.util.fromJson
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.*
import java.nio.charset.Charset
import javax.inject.Inject

class GetMeUseCase @Inject constructor(
    private val userApiRepository: UserApiRepository,
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        userDataRepository.getUser()?.let { user ->
            emit(Resource.Success(user))
        }

        emitAll(
            userApiRepository.getUserByToken().filterNot {
                it is Resource.Loading
            }.onEach {
                it.data?.let { user ->
                    userDataRepository.storeUser(user)
                }
            }
        )
    }
}