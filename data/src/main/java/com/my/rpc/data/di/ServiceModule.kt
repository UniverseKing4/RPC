/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * ServiceModule.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.data.di

import com.my.rpc.data.rpc.RpcRPC
import com.my.rpc.domain.interfaces.Logger
import com.my.rpc.domain.repository.RpcRepository
import com.my.rpc.preference.Prefs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import rpc.gateway.DiscordWebSocket
import rpc.gateway.DiscordWebSocketImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {
    @Provides
    fun providesDiscordWebsocket(
        logger: Logger
    ): DiscordWebSocket =
        DiscordWebSocketImpl(Prefs[Prefs.TOKEN, ""], logger)

    @Provides
    fun provideRpcRpc(
        rpcRepository: RpcRepository,
        discordWebSocket: DiscordWebSocket,
        logger: Logger
    ) = RpcRPC(Prefs[Prefs.TOKEN, ""], rpcRepository, discordWebSocket, logger)

    @Provides
    fun providesCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }
}