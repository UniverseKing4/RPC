/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * AppModule.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.data.di

import com.my.rpc.data.BuildConfig
import com.my.rpc.data.remote.ApiService
import com.my.rpc.data.remote.Base
import com.my.rpc.data.remote.Discord
import com.my.rpc.data.remote.Github
import com.my.rpc.data.remote.Imgur
import com.my.rpc.data.remote.ImgurApiService
import com.my.rpc.data.repository.RpcRepositoryImpl
import com.my.rpc.domain.repository.RpcRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @Base
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Provides
    @Singleton
    @Discord
    fun provideDiscordBaseUrl() = BuildConfig.DISCORD_API_BASE_URL

    @Provides
    @Singleton
    @Github
    fun provideGithubBaseUrl() = BuildConfig.GITHUB_API_BASE_URL

    @Provides
    @Singleton
    @Imgur
    fun provideImgurBaseUrl() = BuildConfig.IMGUR_API_BASE_URL

    @Provides
    fun provideJson() = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Provides
    fun provideHttpClient(
        json: Json,
        kLogger: com.my.rpc.domain.interfaces.Logger
    ): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(json)
            }
            install(HttpTimeout) {
                connectTimeoutMillis = 30_000
                requestTimeoutMillis = 30_000
                socketTimeoutMillis = 30_000
            }
            install(Logging) {
                level = LogLevel.HEADERS
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
                logger = object : Logger {
                    override fun log(message: String) {
                        kLogger.d("Ktor", message)
                    }
                }
            }
        }
    }

    @Provides
    fun providesRpcRepository(
        apiService: ApiService,
        imgurApiService: ImgurApiService
    ): RpcRepository {
        return RpcRepositoryImpl(apiService, imgurApiService)
    }
}