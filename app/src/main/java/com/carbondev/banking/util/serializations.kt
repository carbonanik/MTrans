package com.carbondev.banking.util

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@ExperimentalSerializationApi
val json = Json {
    encodeDefaults = true
    ignoreUnknownKeys = true
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> T.toJson() =
    try {
        json.encodeToString(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }


@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> String.fromJson(): T? =
    try {
        json.decodeFromString<T?>(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
