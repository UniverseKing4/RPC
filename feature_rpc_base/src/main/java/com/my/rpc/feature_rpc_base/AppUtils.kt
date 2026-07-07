/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * AppUtils.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

@file:Suppress("DEPRECATION")

package com.my.rpc.feature_rpc_base

import android.app.ActivityManager
import android.content.Context
import com.my.rpc.feature_rpc_base.services.Rpc

import javax.inject.Singleton

@Singleton
object AppUtils {
    private lateinit var activityManager: ActivityManager
    fun init(context: Context) {
        activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }


    fun rpcRunning(): Boolean {
        return checkForRunningService<Rpc>()
    }
    private inline fun <reified T : Any> checkForRunningService(): Boolean {
        for (runningServiceInfo in activityManager.getRunningServices(
            Int.MAX_VALUE
        )) {
            if (T::class.java.name == runningServiceInfo.service.className)
                return true
        }
        return false
    }
}