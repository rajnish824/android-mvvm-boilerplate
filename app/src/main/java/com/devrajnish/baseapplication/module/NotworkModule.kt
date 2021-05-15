package com.devrajnish.baseapplication.module

import com.devrajnish.baseapplication.network.createExampleService
import org.koin.dsl.module

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

val networkModule = module {
    single { createExampleService() }
}