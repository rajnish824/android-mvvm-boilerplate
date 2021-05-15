package com.devrajnish.baseapplication.module

import com.devrajnish.baseapplication.ui.dashboard.viewmodel.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

val viewModelModule = module {
    viewModel { DashboardViewModel(get()) }
}