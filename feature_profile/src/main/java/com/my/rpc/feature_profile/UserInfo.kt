/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * UserInfo.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.feature_profile

import com.my.rpc.preference.Prefs
import com.my.rpc.preference.Prefs.USER_BIO
import com.my.rpc.preference.Prefs.USER_ID
import com.my.rpc.preference.Prefs.USER_NITRO
import rpc.gateway.DiscordWebSocket
import rpc.gateway.DiscordWebSocketImpl
import rpc.gateway.entities.Payload

suspend fun getUserInfo(token: String, onInfoSaved: () -> Unit) {
    val discordWebSocket: DiscordWebSocket = object: DiscordWebSocketImpl(token){
        override fun Payload.handleDispatch(jsonString: String) {
            if (this.t.toString() == "READY"){
                val user = decodeReady(jsonString)?.user ?: return
                Prefs[USER_ID] = user.id
                Prefs[USER_BIO] = user.bio
                Prefs[USER_NITRO] = user.premiumType in 1..3
                close()
                onInfoSaved()
            }
        }
    }
    discordWebSocket.connect()
}
