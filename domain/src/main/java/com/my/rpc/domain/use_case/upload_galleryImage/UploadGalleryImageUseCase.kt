/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * UploadGalleryImageUseCase.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.domain.use_case.upload_galleryImage

import com.my.rpc.domain.repository.RpcRepository
import java.io.File
import javax.inject.Inject

class UploadGalleryImageUseCase @Inject constructor(
    private val rpcRepository: RpcRepository
) {
    suspend operator fun invoke(file: File): String? {
        return try {
            file.deleteOnExit()
            rpcRepository.uploadImage(file)
        } catch (ex: Exception) {
            null
        }
    }
}