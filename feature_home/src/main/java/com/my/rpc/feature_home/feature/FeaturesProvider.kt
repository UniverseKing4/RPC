/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * FeaturesProvider.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.feature_home.feature

import android.content.Intent
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.my.rpc.feature_rpc_base.AppUtils
import com.my.rpc.feature_rpc_base.services.Rpc
import com.my.rpc.navigation.Routes
import com.my.rpc.preference.Prefs
import com.my.rpc.resources.R

@Composable
fun homeFeaturesProvider(
    navigateTo: (String) -> Unit,
    hasUsageAccess: MutableState<Boolean>,
    hasNotificationAccess: MutableState<Boolean>,
    userVerified: Boolean,
): List<HomeFeature> {
    val ctx = LocalContext.current
    return listOf(
        HomeFeature(
            title = "RPC",
            icon = R.drawable.ic_dev_rpc,
            route = Routes.RPC,
            onClick = { navigateTo(it) },
            isChecked = AppUtils.rpcRunning(),
            onCheckedChange = {
                if (it) {
                    ctx.startService(Intent(ctx, Rpc::class.java))
                } else
                    ctx.stopService(Intent(ctx, Rpc::class.java))
            },
            shape = RoundedCornerShape(20.dp, 44.dp, 20.dp, 44.dp),
            showSwitch = hasUsageAccess.value && hasNotificationAccess.value && userVerified,
            tooltipText = stringResource(id = R.string.main_rpc_details),
            featureDocsLink = ToolTipContent.RPC_DOCS_LINK
        )
    )
}
