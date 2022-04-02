package com.carbondev.banking.domail.use_case

import com.carbondev.banking.data.dto.UpdateUserRequest
import com.carbondev.banking.data.dto.UpdateProfileResponse
import com.carbondev.banking.repository.AuthApiRepository
import com.carbondev.banking.repository.UserDataRepository
import com.carbondev.banking.util.Resource
import com.carbondev.banking.util.fromJson
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.nio.charset.Charset
import javax.inject.Inject

//class UpdateProfileUseCase @Inject constructor(
//    private val authApiRepository: AuthApiRepository,
//    private val userDataRepository: UserDataRepository
//) {
//    operator fun invoke(
//        name: String,
//        email: String,
//        phone_no: String,
//        photo: String?,
//        nid: String,
//        password: String
//    ): Flow<Resource<UpdateProfileResponse>> = flow<Resource<UpdateProfileResponse>> {
//
//        emit(Resource.Loading())
//
//        val res = try {
//
//            val userId = userDataRepository.getUser()?.id
//
//            if (userId != null) {
//                val token = userDataRepository.getToken()
//
//                val updateProfileRequest = UpdateUserRequest(
//                    name = name,
//                    nid = nid,
//                    password = password,
//                    phoneNo = phone_no,
//                    email = email,
//                    photo = photo
//                )
//
//                val response = authApiRepository
//                    .updateProfile(updateProfileRequest, userId.toString())
//
//                response.user?.let { userDataRepository.storeUser(it) }
//                Resource.Success(response)
//            } else {
//                Resource.Error("Authentication Failed")
//            }
//
//        }   catch (e: ResponseException) {
//            e.printStackTrace()
//
//            val message = e.response.readText(Charset.defaultCharset())
//            val errorMsg = message.fromJson<ErrorResponse>()?.msg
//                ?: message.fromJson<ErrorResponseWithArray>()?.msg?.toString()
//
//            Resource.Error(message = errorMsg ?: "Internal Error Occurred: Didn't updated")
//
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Resource.Error(message = e.message ?: "Internal Error Occurred: Didn't updated")
//        }
//
//        // emitting response
//        emit(res)
//    }
//}