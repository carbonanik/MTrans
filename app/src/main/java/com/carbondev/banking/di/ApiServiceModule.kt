package com.carbondev.banking.di

import com.carbondev.banking.data.remote.api.auth.AuthApiService
import com.carbondev.banking.data.remote.api.auth.AuthApiServiceImpl
import com.carbondev.banking.data.remote.api.transaction.TransactionApiService
import com.carbondev.banking.data.remote.api.transaction.TransactionApiServiceImpl
import com.carbondev.banking.data.remote.api.user.UserApiService
import com.carbondev.banking.data.remote.api.user.UserApiServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {

    @Provides
    @Singleton
    fun provideHttpClient() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    @Provides
    @Singleton
    fun provideAuthApiService(client: HttpClient): AuthApiService {
        return AuthApiServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideUserApiService(client: HttpClient): UserApiService {
        return UserApiServiceImpl(client)
    }

    @Provides
    @Singleton
    fun provideTransactionApiService(client: HttpClient): TransactionApiService {
        return TransactionApiServiceImpl(client)
    }
}