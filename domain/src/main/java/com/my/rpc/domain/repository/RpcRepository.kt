/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * RpcRepository.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */
package com.my.rpc.domain.repository

import com.my.rpc.domain.model.Contributor
import com.my.rpc.domain.model.Game
import com.my.rpc.domain.model.release.Release
import com.my.rpc.domain.model.user.User
import java.io.File

interface RpcRepository {
    suspend fun getImage(url: String): String?
    suspend fun uploadImage(file: File): String?
    suspend fun getGames(): List<Game>
    suspend fun getUser(userid: String): User
    suspend fun getContributors(): List<Contributor>
    suspend fun checkForUpdate(): Release
}
