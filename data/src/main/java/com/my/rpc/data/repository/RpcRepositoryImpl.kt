/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * RpcRepositoryImpl.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.data.repository

import com.my.rpc.data.remote.ApiService
import com.my.rpc.data.remote.GamesResponse
import com.my.rpc.data.remote.ImgurApiService
import com.my.rpc.data.remote.toGame
import com.my.rpc.data.rpc.Constants
import com.my.rpc.data.utils.toAttachmentAsset
import com.my.rpc.data.utils.toExternalAsset
import com.my.rpc.data.utils.toImageURL
import com.my.rpc.domain.model.Contributor
import com.my.rpc.domain.model.Game
import com.my.rpc.domain.model.release.Release
import com.my.rpc.domain.model.user.User
import com.my.rpc.domain.repository.RpcRepository
import com.my.rpc.preference.Prefs
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import java.io.File
import javax.inject.Inject

class RpcRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val imgurApi: ImgurApiService,
) : RpcRepository {

    override suspend fun getImage(url: String): String? {
        return if (Prefs[Prefs.USE_IMGUR, false]) {
            imgurApi.getImage(url, Prefs[Prefs.TOKEN]).getOrNull()?.toExternalAsset()
        } else {
            api.getImage(url).getOrNull()?.toAttachmentAsset()
        }
    }

    override suspend fun uploadImage(file: File): String? {
        return if (Prefs[Prefs.USE_IMGUR, false]) {
            imgurApi.uploadImage(file, Prefs[Prefs.IMGUR_CLIENT_ID, Constants.IMGUR_CLIENT_ID])
                .getOrNull()?.toImageURL()?.let { this.getImage(it) }
        } else {
            api.uploadImage(file).getOrNull()?.toAttachmentAsset()
        }
    }

    override suspend fun getGames(): List<Game> {
        return api.getGames().getOrNull()?.body<List<GamesResponse>>()?.map { it.toGame() }
            ?: emptyList()
    }

    override suspend fun getUser(userid: String): User {
        return api.getUser(userid).getOrNull()?.body() ?: User()
    }

    override suspend fun getContributors(): List<Contributor> {
        return api.getContributors().getOrNull()?.body() ?: emptyList()
    }

    override suspend fun checkForUpdate(): Release {
        return api.checkForUpdate().getOrNull()?.releaseBody() ?: Release()
    }
}

suspend fun HttpResponse.releaseBody(): Release {
    return if (this.status.value == 200) {
        Prefs.saveLatestRelease(this.body())
        this.body()
    } else {
        Prefs.getSavedLatestRelease() ?: Release()
    }
}