package com.devrajnish.baseapplication.repository

import com.devrajnish.baseapplication.network.APIService

/**
 * Created by Rajnish Sharma on 08-02-2021
 */

class DashboardRepository(private val apiService: APIService) {
    suspend fun getExampleFunc() = apiService.getExample()
}