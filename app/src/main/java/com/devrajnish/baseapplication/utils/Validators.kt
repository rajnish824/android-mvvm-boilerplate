package com.devrajnish.baseapplication.utils

object Validators {
    val String.containsDigit: Boolean
        get() = matches(Regex(".*[0-9].*"))


    val String.isAlphanumeric: Boolean
        get() = matches(Regex("[a-zA-Z0-9]*"))
}