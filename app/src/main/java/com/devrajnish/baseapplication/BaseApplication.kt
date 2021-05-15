package com.devrajnish.baseapplication

import android.app.Application
import com.devrajnish.baseapplication.module.repositoryModule
import com.devrajnish.baseapplication.module.appModule
import com.devrajnish.baseapplication.module.networkModule
import com.devrajnish.baseapplication.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApplication: Application() {
    companion object {
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@BaseApplication)
            modules(appModule, networkModule, repositoryModule, viewModelModule)
        }
    }
}