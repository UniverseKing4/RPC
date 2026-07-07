/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * ExternalAsset.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExternalAsset(
    @SerialName("external_asset_path")
    val externalAssetPath: String,
    @SerialName("url")
    val url: String
)