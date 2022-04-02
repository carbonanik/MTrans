package com.carbondev.banking.domail.use_case

import com.carbondev.banking.data.dto.*
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

class RegistrationUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val authApiRepository: AuthApiRepository
) {
    operator fun invoke(registrationRequest: RegistrationRequest) =

        authApiRepository.registration(registrationRequest).onEach { resource ->

            resource.data?.let { authResponse ->
                userDataRepository.storeUser(authResponse.user)
                userDataRepository.storeToken(authResponse.token)
            }
        }
}