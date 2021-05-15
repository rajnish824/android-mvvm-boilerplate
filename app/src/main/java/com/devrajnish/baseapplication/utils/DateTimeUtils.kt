package com.devrajnish.baseapplication.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {
    fun String.toDate(format: String): Date? {
        val dateFormatter = SimpleDateFormat(format, Locale.US)
        return try {
            dateFormatter.parse(this)
        } catch (e: ParseException) {
            null
        }
    }

    fun Date.toString(format: String): String {
        val dateFormatter = SimpleDateFormat(format, Locale.US)
        return dateFormatter.format(this)
    }
}