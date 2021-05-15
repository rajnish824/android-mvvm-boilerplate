package com.devrajnish.baseapplication.module

import com.devrajnish.baseapplication.repository.DashboardRepository
import org.koin.dsl.module

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

val repositoryModule = module {
    single { DashboardRepository(get()) }
}