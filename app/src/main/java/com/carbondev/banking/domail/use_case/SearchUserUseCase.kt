package com.carbondev.banking.domail.use_case

import com.carbondev.banking.data.dto.GetUserRequest
import com.carbondev.banking.data.dto.SearchUserResponse
import com.carbondev.banking.data.dto.UpdateUserRequest
import com.carbondev.banking.repository.AuthApiRepository
import com.carbondev.banking.repository.UserApiRepository
import com.carbondev.banking.repository.UserDataRepository
import com.carbondev.banking.util.Resource
import com.carbondev.banking.util.fromJson
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.flow
import java.nio.charset.Charset
import javax.inject.Inject

//class SearchUserUseCase @Inject constructor(
//    private val userApiRepository: UserApiRepository,
//    private val authApiRepository: AuthApiRepository
//) {
//    operator fun invoke(name: String) =
//        flow<Resource<SearchUserResponse>> {
//
//            emit(Resource.Loading())
//
//            try {
//
//                val response = userApiRepository.searchUserByName(name)
//                println(response)
//                Resource.Success(response)
//
//            } catch (e: ResponseException){
//                e.printStackTrace()
//                val message = e.response.readText(Charset.defaultCharset())
//                Resource.Error(message = message ?:"Internal Error Occurred")
//
//            } catch (e: Exception){
//                e.printStackTrace()
//                Resource.Error(message = e.message?:"Internal Error Occurred")
//            }
//        }
//}