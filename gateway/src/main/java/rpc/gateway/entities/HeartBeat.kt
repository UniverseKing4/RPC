/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * HeartBeat.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package rpc.gateway.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Heartbeat(
    @SerialName("heartbeat_interval")
    val heartbeatInterval: Long,
)