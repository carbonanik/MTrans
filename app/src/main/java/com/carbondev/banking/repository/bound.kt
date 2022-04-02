package com.carbondev.banking.repository

import com.carbondev.banking.util.Resource
import io.ktor.client.features.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.flow
import java.nio.charset.Charset

inline fun <T> bound(
    crossinline fetch: suspend () -> T
) =

    flow<Resource<T>> {

        emit(Resource.Loading<T>())

        val response = try {
            Resource.Success(fetch())

        } catch (e: ResponseException) {
            Resource.Error(
                e.response.readText(Charset.defaultCharset())
            )
        }

        emit(response)
    }
