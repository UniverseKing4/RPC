/*
 *
 *  ******************************************************************
 *  *  * Copyright (C) 2022
 *  *  * Qualifier.kt is part of Rpc
 *  *  *  and can not be copied and/or distributed without the express
 *  *  * permission of yzziK(Vaibhav)
 *  *  *****************************************************************
 *
 *
 */

package com.my.rpc.data.remote

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Base

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Discord

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Github

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Imgur
