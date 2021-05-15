package com.devrajnish.baseapplication.base

import com.devrajnish.baseapplication.model.ErrorResponse
import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

interface BaseErrorInterface {
    fun showError(throwable: Throwable) {
        when (throwable) {
            is HttpException -> {
                val response = throwable.response()

                val errorResponse =
                    Gson().fromJson(response?.errorBody()!!.string(), ErrorResponse::class.java)

                when (response.code()) {
                    429 -> onTooManyRequests()
                    404 -> onNotFound(errorResponse)
                    else -> showOtherError(errorResponse)
                }

            }
            is SocketTimeoutException -> showSocketTimeoutError()
            is IOException -> showInternetError()
        }
    }

    fun onTooManyRequests() {
        showOtherError(ErrorResponse())
    }

    fun showInternetError() {
        showOtherError(ErrorResponse())
    }

    fun showSocketTimeoutError() {
        showOtherError(ErrorResponse())
    }

    fun onNotFound(errorResponse: ErrorResponse) {
        showOtherError(errorResponse)
    }


    fun showOtherError(errorResponse: ErrorResponse)
}