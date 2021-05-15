package com.devrajnish.baseapplication.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.devrajnish.baseapplication.BaseApplication
import com.devrajnish.baseapplication.model.AppConfig
import com.devrajnish.baseapplication.utils.AppConst.PREF_APP_CONFIG
import com.devrajnish.baseapplication.utils.AppConst.PREF_FILE
import com.devrajnish.baseapplication.utils.AppConst.PREF_SAMPLE
import com.squareup.moshi.Moshi
import org.koin.java.KoinJavaComponent

object PrefUtils {
    private fun initialize(context: Context = BaseApplication.instance): SharedPreferences {
        return context.getSharedPreferences(PREF_FILE, MODE_PRIVATE)
    }

    fun setSampleString(data: String) {
        val pref = initialize().edit()
        pref.putString(PREF_SAMPLE, data)
        pref.apply()
    }

    fun getSampleString(): String? {
        val pref = initialize()
        if (!pref.contains(PREF_SAMPLE)) {
            return null
        }

        return pref.getString(PREF_SAMPLE, "")
    }

    fun setSampleObject(appConfig: AppConfig) {
        val pref = initialize().edit()
        val configAdapter = (KoinJavaComponent.getKoin().get() as Moshi).adapter(AppConfig::class.java)
        pref.putString(PREF_APP_CONFIG, configAdapter.toJson(appConfig))
        pref.apply()
    }

    fun getSampleObject(): AppConfig {
        val pref = initialize()
        if (!pref.contains(PREF_APP_CONFIG)) {
            return AppConfig()
        }
        val configAdapter = (KoinJavaComponent.getKoin().get() as Moshi).adapter(AppConfig::class.java)
        return configAdapter.fromJson(pref.getString(PREF_APP_CONFIG, "").orEmpty()) ?: AppConfig()
    }
}