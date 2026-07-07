/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * LoginUiState.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.feature_profile.ui.login

sealed interface LoginUiState {
    object InitialState: LoginUiState
    object OnLoginClicked: LoginUiState
    object OnLoginCompleted: LoginUiState
}