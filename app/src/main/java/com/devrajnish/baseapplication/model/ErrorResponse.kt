package com.devrajnish.baseapplication.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
        @SerializedName("code")
        var code: Int = 0,
        @SerializedName("message")
        var message: String? = null,
        @SerializedName("errors")
        var errors: String? = null
)