package com.carbondev.banking.data

object HttpRoutes {

    private const val BASE_URL = "http://10.0.2.2:8080"

    const val LOGIN = "$BASE_URL/auth/login"
    const val REGISTRATION = "$BASE_URL/auth/registration"

    const val GET_ALL_USER = "$BASE_URL/user/get-all"
    const val GET_USER_BY_TOKEN = "$BASE_URL/user/by-token"
    const val GET_USER_BY_ID = "$BASE_URL/user/by-id"
    const val GET_USER_BY_USERNAME = "$BASE_URL/user/by-username"
    const val SEARCH_USER_BY_NAME = "$BASE_URL/user/search-name"
    const val UPDATE_USER = "$BASE_URL/user/update"
    const val DELETE_USER = "$BASE_URL/user/delete"

    const val CREATE_TRANSACTION = "$BASE_URL/transaction/create"
    const val GET_ALL_TRANSACTION = "$BASE_URL/transaction/get-all"
    const val GET_TRANSACTION_BY_ID = "$BASE_URL/transaction/by-id"
    const val GET_TRANSACTION_FOR_USER = "$BASE_URL/transaction/for-user"
    const val GET_TRANSACTION_BY_SENDER = "$BASE_URL/transaction/by-sender"
    const val GET_TRANSACTION_BY_RECEIVER = "$BASE_URL/transaction/by-receiver"

}
