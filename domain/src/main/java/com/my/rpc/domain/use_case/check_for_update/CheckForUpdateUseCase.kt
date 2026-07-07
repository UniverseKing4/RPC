/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * CheckForUpdateUseCase.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.domain.use_case.check_for_update

import com.my.rpc.domain.model.Resource
import com.my.rpc.domain.model.release.Release
import com.my.rpc.domain.repository.RpcRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CheckForUpdateUseCase @Inject constructor(
    private val repository: RpcRepository
) {
    operator fun invoke(): Flow<Resource<Release>> = flow {
        try {
            emit(Resource.Loading())
            val release = repository.checkForUpdate()
            emit(Resource.Success(release))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}