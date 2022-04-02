package com.carbondev.banking.domail.use_case

import com.carbondev.banking.data.dto.LoginRequest
import com.carbondev.banking.data.dto.LoginResponse
import com.carbondev.banking.repository.AuthApiRepository
import com.carbondev.banking.repository.UserDataRepository
import com.carbondev.banking.util.Resource
import com.carbondev.banking.util.fromJson
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import java.nio.charset.Charset
import javax.inject.Inject
class LoginUseCase @Inject constructor(
    private val authApiRepository: AuthApiRepository,
    private val userDataRepository: UserDataRepository
) {
    operator fun invoke(loginRequest: LoginRequest) =
        authApiRepository.login(loginRequest).onEach { resource ->

            resource.data?.let { authRes ->
                userDataRepository.storeUser(authRes.user)
                userDataRepository.storeToken(authRes.token)
            }
        }

}


