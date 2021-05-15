package com.devrajnish.baseapplication.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.browser.customtabs.CustomTabsIntent
import com.devrajnish.baseapplication.BaseApplication
import kotlin.math.roundToInt

object AppUtils {
    fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            BaseApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_LOWPAN) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> true
            else -> false
        }
    }

    fun launchChrome(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(context, Uri.parse(url))
    }

    fun dpToPx(context: Context, dp: Int) =
        (dp * (context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()

    fun getUriFromRowFile(context: Context, id: Int): Uri =
        Uri.parse("android.resource://" + context.packageName + "/" + id)

    fun addCleanIntent() = Intent.FLAG_ACTIVITY_NEW_TASK or
            Intent.FLAG_ACTIVITY_CLEAR_TOP or
            Intent.FLAG_ACTIVITY_SINGLE_TOP or
            Intent.FLAG_ACTIVITY_CLEAR_TASK

    fun showKeyboard(context: Context, view: View?) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }
}