package com.devrajnish.baseapplication.model

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

data class DataWrapper<T>(val response: T? = null, val isLoading: Boolean = false, val exception: Throwable? = null)