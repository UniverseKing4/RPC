/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * GetUserUseCase.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.domain.use_case.get_user

import com.my.rpc.domain.model.Resource
import com.my.rpc.domain.model.user.User
import com.my.rpc.domain.repository.RpcRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val rpcRepository: RpcRepository
) {
    operator fun invoke(userid: String): Flow<Resource<User>> = flow {
        try {
            emit(Resource.Loading())
            val user = rpcRepository.getUser(userid)
            emit(Resource.Success(user))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}