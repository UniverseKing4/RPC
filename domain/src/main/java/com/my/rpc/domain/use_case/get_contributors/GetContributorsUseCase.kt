/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * GetContributorsUseCase.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.domain.use_case.get_contributors

import com.my.rpc.domain.model.Contributor
import com.my.rpc.domain.model.Resource
import com.my.rpc.domain.repository.RpcRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetContributorsUseCase @Inject constructor(
    private val rpcRepository: RpcRepository
) {
    operator fun invoke(): Flow<Resource<List<Contributor>>> = flow {
        try {
            emit(Resource.Loading())
            val contributors = rpcRepository.getContributors()
            emit(Resource.Success(contributors))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}