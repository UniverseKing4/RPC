/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * Game.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * Game.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.domain.model

import androidx.compose.runtime.Immutable

@Immutable
data class Game(
    val platform: String,
    val small_image: String,
    val large_image: String?,
    val game_title: String
)
