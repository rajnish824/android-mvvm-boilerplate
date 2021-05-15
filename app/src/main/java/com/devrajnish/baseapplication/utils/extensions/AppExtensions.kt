package com.devrajnish.baseapplication.utils.extensions

import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList

fun List<String>.toStringWithComma(): String {
    var string = ""
    forEach() {
        string = "$string, $it"
    }

    return string.substring(2)
}

fun Int?.orElse(): Int {
    return this ?: 0
}

fun Int?.orElse(defaultValue: Int): Int {
    return this ?: defaultValue
}

fun <T> ArrayList<T>.togglePush(item: T) {
    if (contains(item)) {
        remove(item)
    } else {
        add(item)
    }
}

fun <T> List<T>.second() = this[1]

fun Double.toPrice(): String {
    val pattern = "#,###.00"
    val decimalFormat = DecimalFormat(pattern)
    decimalFormat.groupingSize = 3

    return "€" + decimalFormat.format(this)
}

fun Int.centsToDollars(): Double = this.toDouble() / 100.0

fun Int.centsToDollarsFormat(currency: String): String {
    val dollars = this / 100
    val cents = this % 100
    return String.format(Locale.US, "%s%d.%02d", currency, dollars, cents)
}